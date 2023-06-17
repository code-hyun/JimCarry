package com.app.jimcarry.controller;

import com.app.jimcarry.domain.dto.InquiryDTO;
import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.Criteria;
import com.app.jimcarry.domain.vo.InquiryFileVO;
import com.app.jimcarry.domain.vo.InquiryVO;
import com.app.jimcarry.domain.vo.UserVO;
import com.app.jimcarry.service.InquiryFileService;
import com.app.jimcarry.service.InquiryService;
import com.app.jimcarry.service.NoticeService;
import com.app.jimcarry.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/notice/*")
public class NoticeController {
    private final InquiryService inquiryService;
    private final UserService userService;
    private final NoticeService noticeService;
    private final InquiryFileService inquiryFileService;
    private final UserVO userVO;
    private final InquiryFileVO inquiryFileVO;

    @GetMapping("faq")
    public String faq() { return "notice/faq";}

    @GetMapping("detail/{noticeId}")
    public String detail(@PathVariable("noticeId") Long noticeId, Model model) {
        model.addAttribute("notice", noticeService.getNotice(noticeId));

        return "notice/notice-detail";}

  /*  @GetMapping("detail")
    public String detail() {

        return "notice/notice-detail";}*/

    @GetMapping("list")
    public String list(Criteria criteria, Model model){
        /* 한 페이지에 보여줄 게시글 개수 */
        int amount = 5;
        /* 검색된 결과의 총 개수 */
        int total = 0;

        PageDTO pageDTO = null;

//         페이지 번호가 없을 때, 디폴트 1페이지
        if (criteria.getPage() == 0) {
            criteria.create(1, amount);
        } else criteria.create(criteria.getPage(), amount);

        total = noticeService.getTotal();
        pageDTO = new PageDTO().createPageDTO(criteria, total, new SearchDTO());
        model.addAttribute("total", total);
        model.addAttribute("notices", noticeService.getList(pageDTO));
        model.addAttribute("pagination", pageDTO);

        //공지사항 전체 갯수
        model.addAttribute("totalNotice", noticeService.getTotal());


        return "notice/notice-list";
    }

    @GetMapping("write")
    public String write(Model model, HttpSession httpSession) {
        model.addAttribute("userPhone", userService.getUser(((UserVO)httpSession.getAttribute("user")).getUserId()).getUserPhone());
        model.addAttribute("userId", userService.getUser(((UserVO)httpSession.getAttribute("user")).getUserId()));

        return "notice/qna-write";}

    /*파일 업로드 포함*/
    @PostMapping("write")
    @ResponseBody
    public String writeRegister(@RequestBody InquiryDTO inquiryDTO, HttpSession httpSession) throws IOException{
        inquiryDTO.setUserId(((UserVO)httpSession.getAttribute("user")).getUserId());

        inquiryService.registerInquiry(inquiryDTO);



     /*   userService.login;*/

      /*  문의 등록후 이동페이지*/
        return "/notice/list";

    }

    @GetMapping("info")
    public String info() { return "notice/service-use-Info";}


}
