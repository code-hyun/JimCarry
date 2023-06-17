package com.app.jimcarry.controller;

import com.app.jimcarry.domain.vo.MailTO;
import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.http.HttpResponse;

@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

//    회원가입 페이지 이동
    @GetMapping("join")
    public String join() {
        return "joinLogin/joinForm";
    }

//    카카오, 네이버, 일반 회원가입 선택 페이지 이동
    @GetMapping("join-select")
    public String joinSelect() {
        return "joinLogin/join-select";
    }

//    네이버 회원가입 콜백 페이지로 이동
    @GetMapping("callback")
    public String callback() {
        return "joinLogin/callback";
    }

//    일반 회원가입 페이지로 이동
    @PostMapping("join")
    public RedirectView join(UserVO userVO, HttpSession session) {
        session.invalidate();
        userService.registerUser(userVO);
        return new RedirectView("/user/login");
    }

//    로그인 페이지로 이동
    @GetMapping("login")
    public String login() {
        return "joinLogin/login";
    }

//    로그인
    @PostMapping("login")
    public RedirectView login(String userIdentification, String userPassword, HttpSession session, HttpServletResponse response) {
//        아이디와 패스워드로 비교한 결과를 userVO객체로 받음
        UserVO userVO = userService.login(userIdentification, userPassword);

//        로그인한 사람의 아이디가 admin이라면
//        그 사람의 정보를 user라는 이름으로 세션에 담고
//        관리자 페이지로 이동

        if(userVO == null || userVO.getUserStatus() != 0) {
            return new RedirectView("/user/login?result=fail");
        }

        if(userVO.getUserIdentification().equals("admin")) {
            session.setAttribute("user", userVO);
            return new RedirectView("/admin/user");
        }

        session.setAttribute("user", userVO);

        /* 쿠키 값 담기 */
        Cookie userIdcookie = new Cookie("userId", String.valueOf(userVO.getUserId()));
        Cookie userNamecookie = new Cookie("userName", String.valueOf(userVO.getUserName()));
        userIdcookie.setMaxAge(60 * 60 * 8);
        userIdcookie.setPath("/");
        userNamecookie.setMaxAge(60 * 60 * 8);
        userNamecookie.setPath("/");
        response.addCookie(userIdcookie);
        response.addCookie(userNamecookie);

        return new RedirectView("/main/");
    }

    @GetMapping("find-id-phone")
    public String findIdPhone() {
        return "joinLogin/find-id-phone";
    }

    @PostMapping("find-id-phone")
    public RedirectView findIdPhone(String userName, String userPhone, RedirectAttributes redirectAttributes) {
        String userIdentification = null;

        UserVO userVO = userService.findIdByPhone(userIdentification, userName, userPhone);

        if(userVO == null) {
            return new RedirectView("/user/find-id-phone?result=fail");
        }
        redirectAttributes.addFlashAttribute("userIdentification", userVO.getUserIdentification());

        return new RedirectView("/user/find-id-result");
    }

    @GetMapping("find-id-result")
    public String findIdResult() {
        return "joinLogin/find-id-result";
    }

    @GetMapping("find-id-email")
    public String findIdEmail() {
        return "joinLogin/find-id-email";
    }

    @PostMapping("find-id-email")
    public RedirectView findIdEmail(String userName, String userEmail, RedirectAttributes redirectAttributes) {
        String userIdentification = null;

        UserVO userVO = userService.findIdByEmail(userIdentification, userName, userEmail);

        if(userVO == null) {
            return new RedirectView("/user/find-id-email?result=fail");
        }
        redirectAttributes.addFlashAttribute("userIdentification", userVO.getUserIdentification());
        return new RedirectView("/user/find-id-result");
    }

    @GetMapping("find-password-phone")
    public String findPasswordPhone() {
        return "joinLogin/find-password-phone";
    }

    @PostMapping("find-password-phone")
    public RedirectView findPasswordPhone(String userIdentification, String userPhone) {
        String userName = null;

        if(userService.findIdByPhone(userIdentification, userName, userPhone) == null) {
            return new RedirectView("/user/find-password-phone?result=fail");
        }

        String randomKey = userService.randomKey();

        //    비밀번호 변경 이메일 발송시 랜덤 키 값 컬럼에 저장
        //    비밀번호 변경 완료 시 랜덤 키 컬럼 값 삭제
        userService.updateUserRandomKey(userIdentification, randomKey);

        return new RedirectView("/user/changePassword?userIdentification=" + userIdentification + "&userRandomKey=" + randomKey);
    }

    @GetMapping("find-password-email")
    public String findPasswordEmail() {
        return "joinLogin/find-password-email";
    }

    @GetMapping("changePassword")
    public String changePassword(String userIdentification, String userRandomKey, String userEmail) {
        if(!userService.findByIdentification(userIdentification, userEmail).getUserRandomKey().equals(userRandomKey)) {
            return "/";
        }

        userService.updateUserRandomKey(userIdentification, null);
        return "joinLogin/changePassword";
    }

    @PostMapping("changePassword")
    public RedirectView changePasswordOK(String userIdentification, String userPassword) {
        userService.updateUserPassword(userIdentification, userPassword);
        return new RedirectView("/user/login");
    }

    @GetMapping("find-password-emailsend")
    public String findPasswordEmailSend() {
        return "joinLogin/find-password-emailsend";
    }

    @PostMapping("find-password-email")
    public RedirectView findPasswordEmail(String userIdentification, String userEmail, RedirectAttributes redirectAttributes) {
        String userName = null;

        if(userService.findIdByEmail(userIdentification, userName, userEmail) == null) {
            return new RedirectView("/user/find-password-email?result=fail");
        }

        String randomKey = userService.randomKey();

        //    비밀번호 변경 이메일 발송시 랜덤 키 값 컬럼에 저장
        //    비밀번호 변경 완료 시 랜덤 키 컬럼 값 삭제
        userService.updateUserRandomKey(userIdentification, randomKey);

        MailTO mailTO = new MailTO();
        mailTO.setAddress(userEmail);
        mailTO.setTitle("새 비밀번호 설정 링크입니다.");
        mailTO.setMessage("링크: http://www.jimcarry.site/user/changePassword?userIdentification=" + userIdentification + "&userRandomKey=" + randomKey);
        userService.sendMail(mailTO);

        redirectAttributes.addFlashAttribute("userEmail", userEmail);
        return new RedirectView("/user/find-password-emailsend");
    }

    @GetMapping("logout")
    public RedirectView logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        /* 로그아웃 시 쿠키 삭제 */
        for(Cookie cookie : request.getCookies()) {
            cookie.setValue("");
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        session.invalidate();
        return new RedirectView("/main/");
    }


    @GetMapping("kakao")
    public RedirectView kakaoJoin(String code, HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
        String token = userService.getKaKaoAccessToken(code, "join");
        UserVO kakaoInfo = userService.getKakaoInfo(token);

        kakaoInfo.setUserStatus(1);

        String userIdentification = null;

        UserVO userVO = userService.findByIdentification(userIdentification, kakaoInfo.getUserEmail());

        //    클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
        if (userVO == null || userVO.getUserStatus() != 1) {
            redirectAttributes.addFlashAttribute("kakaoInfo", kakaoInfo);
            return new RedirectView("/user/join");
        }

        session.setAttribute("user", userVO);
        return new RedirectView("/main/");
    }

    @GetMapping("kakao-login")
    public RedirectView kakaoLogin(String code, HttpSession session) throws Exception {
        String userIdentification = null;

        String token = userService.getKaKaoAccessToken(code, "login");
        userService.getKakaoInfo(token);

        UserVO kakaoInfo = userService.getKakaoInfo(token);
        UserVO userVO = userService.findByIdentification(userIdentification, kakaoInfo.getUserEmail());

        if(userVO == null || userVO.getUserStatus() != 1){
            return new RedirectView("/user/login?result=fail");
        }

        session.setAttribute("user", userVO);
        return new RedirectView("/main/");
    }
//
//    @GetMapping("/logout/kakao")
//    public void kakaoLogout(HttpSession session){
//        log.info("logout");
//        userService.logoutKakao((String)session.getAttribute("token"));
//        session.invalidate();
//    }
}
