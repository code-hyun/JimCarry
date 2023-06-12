package com.app.jimcarry.controller;

import com.app.jimcarry.aspect.annotation.CheckLogin;
import com.app.jimcarry.aspect.annotation.MypageHeaderValue;
import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.ReviewDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.dto.StorageDTO;
import com.app.jimcarry.domain.vo.*;
import com.app.jimcarry.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users/mypage/*")
@RequiredArgsConstructor
@Slf4j
public class MypageController {

    private final UserService userService;
    private final StorageService storageService;
    private final StorageFileService storageFileService;
    private final InquiryService inquiryService;
    private final InquiryFileService inquiryFileService;
    private final ReviewFileService reviewFileService;
    private final ReviewService reviewService;
    private final PaymentService paymentService;
    private final HttpServletRequest request;

    /* ============================== 내 창고 ================================ */
    @MypageHeaderValue
    @GetMapping("mybox")
    public String myBox(Criteria criteria, Model model) {
        Long userId = Optional.ofNullable((UserVO) request.getSession().getAttribute("user")).get().getUserId();
        SearchDTO searchDTO = new SearchDTO().createTypes(new ArrayList<>(Arrays.asList("userId")));
        searchDTO.setUserId(userId);
        int total = storageService.getTotalBy(searchDTO);
        model.addAttribute("storages",
                storageService.getListBy(setPaginationAndReturn(criteria, model, 3, total, searchDTO)));
        return "mypage/myBox";
    }

    /* ============================== 이용중인 창고 ================================ */
    @MypageHeaderValue
    @GetMapping("usage")
    public String usage(Criteria criteria, Model model) {
        Long userId = Optional.ofNullable((UserVO) request.getSession().getAttribute("user")).get().getUserId();
        SearchDTO searchDTO = new SearchDTO().createTypes(new ArrayList<>(Arrays.asList("userId")));
        searchDTO.setUserId(userId);
        int total = paymentService.getTotalBy(searchDTO);
        model.addAttribute("payments",
                paymentService.getListBy(setPaginationAndReturn(criteria, model, 3, total, searchDTO)));
        return "mypage/use-myBox";
    }

    /* ============================== 문의 사항 ================================ */
    @MypageHeaderValue
    @GetMapping("qna")
    public String goQna(Criteria criteria, Model model) {
        Long userId = Optional.ofNullable((UserVO) request.getSession().getAttribute("user")).get().getUserId();
        SearchDTO searchDTO = new SearchDTO().createTypes(new ArrayList<>(Arrays.asList("userId")));
        searchDTO.setUserId(userId);
        int total = inquiryService.getTotalBy(searchDTO);
        model.addAttribute("inquiries",
                inquiryService.getListBy(setPaginationAndReturn(criteria, model, 5, total, searchDTO)));
        return "mypage/my-qna";
    }

    @PostMapping("qna/update")
    public RedirectView updateQna(InquiryVO inquiryVO, String page) {
        Long userId = Optional.ofNullable((UserVO) request.getSession().getAttribute("user")).get().getUserId();

        inquiryVO.setUserId(userId);
        inquiryService.updateInquiry(inquiryVO);
        return new RedirectView("/users/mypage/qna?page=" + page);
    }

    @PostMapping("qna/delete")
    public RedirectView deleteQna(Long inquiryId, String page) {
        inquiryService.removeInquiry(inquiryId);
        return new RedirectView("/users/mypage/qna?page=" + page);
    }

    /* ============================== 파일 ================================ */

    @PostMapping("files/upload")
    @ResponseBody
    public Map<String, Object> upload(@RequestParam("file") List<MultipartFile> multipartFiles) throws IOException {
        return inquiryFileService.uploadFile(multipartFiles);
    }

    @GetMapping("files/display")
    @ResponseBody
    public byte[] display(String fileName) throws IOException {
        return FileCopyUtils.copyToByteArray(new File("/C:/upload", fileName));
    }

    /**
     * @param table 파일 종류를 받아서 제너릭의 타입을 결정하는 변수
     */
    @GetMapping("files/thumbnail/{id}")
    @ResponseBody
    /* T : 제너릭 타입으로, 들어오는 파일 타입으로 바뀜 */
    public <T extends FileVO> List<T> display(@PathVariable Long id, String table) {
        if (table == null) {
            return new ArrayList<>();
        }
        /* table 분기처리 */
        if (table.equals("inquiry")) return (List<T>) inquiryFileService.getList(id);
        else if (table.equals("review")) return (List<T>) reviewFileService.getList(id);

        return new ArrayList<>();
    }

    @PostMapping("files/save/{id}")
    @ResponseBody
    public void saveFile(@RequestBody List<FileVO> files, @PathVariable Long id, String table) {

        if (table.equals("inquiry")) inquiryFileService.registerFile(files, id);
        else if (table.equals("review")) reviewFileService.registerFile(files, id);
    }

    /* ================================ 내 후기 ================================== */
    @MypageHeaderValue
    @GetMapping("review")
    public String review(Criteria criteria, Model model) {
        Long userId = Optional.ofNullable((UserVO) request.getSession().getAttribute("user")).get().getUserId();
        SearchDTO searchDTO = new SearchDTO().createTypes(new ArrayList<>(Arrays.asList("userId")));
        searchDTO.setUserId(userId);
        int total = reviewService.getTotalBy(searchDTO);
        PageDTO pageDTO = setPaginationAndReturn(criteria, model, 5, total, searchDTO);
        model.addAttribute("payments", paymentService.getListBy(pageDTO));
        model.addAttribute("reviews", reviewService.getListBy(pageDTO));

        return "mypage/my-review";
    }

    @PostMapping("review/update")
    @ResponseBody
    public String updateReview(@RequestBody ReviewDTO reviewDTO, @RequestParam String page) {
        Long userId = Optional.ofNullable((UserVO) request.getSession().getAttribute("user")).get().getUserId();
        /* 추후 세션으로 변경 */
        reviewDTO.setUserId(userId);
        reviewService.updateReview(reviewDTO);

        return "/users/mypage/review?page=" + page;
    }

    @PostMapping("review/register")
    @ResponseBody
    public String registerReview(@RequestBody ReviewDTO reviewDTO, @RequestParam String page) {
        Long userId = Optional.ofNullable((UserVO) request.getSession().getAttribute("user")).get().getUserId();
        /* 추후 세션으로 변경 */
        reviewDTO.setUserId(userId);
        reviewService.registerReview(reviewDTO);

        return "/users/mypage/review?page=" + page;
    }

    /* ============================== 회원정보 수정 ================================ */
    @MypageHeaderValue
    @GetMapping("update")
    public String updateUser(Model model) {
        Long userId = Optional.ofNullable((UserVO) request.getSession().getAttribute("user")).get().getUserId();

        UserVO userVO = userService.getUser(userId);
        String userBirth = userVO.getUserBirth();
        String[] births = null;
        model.addAttribute(userVO);

        if (userBirth.contains("-")) {
            births = userVO.getUserBirth().split("-");
        } else if (userBirth.contains("/")) {
            births = userVO.getUserBirth().split("/");
        }

        if(births.length > 0) {
            model.addAttribute("birthFirtst", births[0]);
            model.addAttribute("birthMiddle", births[1]);
            model.addAttribute("birthLast", births[2]);
        } else {
            model.addAttribute("birthFirtst", "");
            model.addAttribute("birthMiddle", "");
            model.addAttribute("birthLast", "");
        }

        return "mypage/my-info";
    }

    @PostMapping("update")
    @ResponseBody
    public RedirectView updateUser(UserVO userVO) {
        UserVO sessionUser = (UserVO)request.getSession().getAttribute("user");
        Long userId = Optional.ofNullable(sessionUser).get().getUserId();
        UserVO temp = userService.getUser(userId);
        userVO.setUserId(userId);
        userVO.setUserAddress(temp.getUserAddress());
        userVO.setUserAddressDetail(temp.getUserAddressDetail());
        userVO.setUserGender(userVO.getUserGender().equals("") ? null : userVO.getUserGender());
        userService.updateUser(userVO);

        sessionUser.setUserName(userVO.getUserName());

        request.getSession().setAttribute("user", sessionUser);

        return new RedirectView("/users/mypage/update");
    }

    @PostMapping("checkIdentification")
    @ResponseBody
    public boolean checkIdentificationDuplicate(@RequestBody Map<String, String> map) {
        Long userId = Optional.ofNullable((UserVO) request.getSession().getAttribute("user")).get().getUserId();
        String userIdentification = map.get("userIdentification");
        /* 나중에 세션으로 수정 */
        if (userService.getUser(userId).getUserIdentification().equals(userIdentification)) {
            return true;
        }

        return userService.checkIdentificationDuplicate(userIdentification);
    }

    @PostMapping("checkEmail")
    @ResponseBody
    public boolean checkEmailDuplicate(@RequestBody Map<String, String> map) {
        Long userId = Optional.ofNullable((UserVO) request.getSession().getAttribute("user")).get().getUserId();
        String userEmail = map.get("userEmail");
        /* 나중에 세션으로 수정 */
        if (userService.getUser(userId).getUserEmail().equals(userEmail)) {
            return true;
        }

        return userService.checkEmailDuplicate(userEmail);
    }

    /* ================================= 회원탈퇴 ================================= */
    @MypageHeaderValue
    @GetMapping("unregister")
    public String unregister() {
        return "mypage/my-withdrawal";
    }

    @PostMapping("checkPassword")
    @ResponseBody
    public boolean checkPassword(@RequestBody Map<String, String> map) {
        Long userId = Optional.ofNullable((UserVO) request.getSession().getAttribute("user")).get().getUserId();
        String userPassword = map.get("userPassword");
        /* 나중에 세션으로 수정 */
        if (userService.getUser(userId).getUserPassword().equals(encryptPassword(userPassword))) {
            return true;
        }


        return false;
    }

    @PostMapping("delete")
    public RedirectView deleteUser(HttpSession session, HttpServletResponse response) {
        Long userId = Optional.ofNullable((UserVO) request.getSession().getAttribute("user")).get().getUserId();
        /* 나중에 세션으로 수정 */

        session.invalidate();

        for (Cookie cookie : request.getCookies()) {
            cookie.setValue("");
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        userService.removeUser(userId);

        /* 메인페이지 주소 작성 필요 */
        return new RedirectView("/main/");
    }

    private String encryptPassword(String arg) {
        return new String(Base64.getEncoder().encode(arg.getBytes()));
    }

    /* =========================================================================== */

    /**
     * pageDTO 세팅
     */
    private PageDTO setPaginationAndReturn(Criteria criteria, Model model, int amount, int total, SearchDTO searchDTO) {
        /* 한 페이지에 보여줄 게시글 개수 */
        /* 검색된 결과의 총 개수 */
        PageDTO pageDTO = null;

//         페이지 번호가 없을 때, 디폴트 1페이지
        if (criteria.getPage() == 0) {
            criteria.create(1, amount);
        } else criteria.create(criteria.getPage(), amount);

        pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);
        model.addAttribute("total", total);
        model.addAttribute("pagination", pageDTO);

        return pageDTO;
    }

}