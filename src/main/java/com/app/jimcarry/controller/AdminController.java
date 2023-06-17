package com.app.jimcarry.controller;

import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.ReviewDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.*;
import com.app.jimcarry.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/*")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final UserService userService;
    private final StorageService storageService;
    private final ReviewService reviewService;
    private final InquiryService inquiryService;
    private final NoticeService noticeService;
    private final PaymentService paymentService;

    /*======================== 회원 관리 ==========================*/
    /*======== 회원 목록 조회, 페이징 처리 ========*/
    @GetMapping("user")
    public String user(Criteria criteria, Model model) {

        /* 한 페이지에 보여줄 게시글 개수 */
        int amount = 5;
        /* 검색된 결과의 총 개수 */
        int total = 0;
        SearchDTO searchDTO = new SearchDTO();

        PageDTO pageDTO = null;

//         페이지 번호가 없을 때, 디폴트 1페이지
        if (criteria.getPage() == 0) {
            criteria.create(1, amount);
        } else criteria.create(criteria.getPage(), amount);

        total= userService.findTotalBy(searchDTO);
        pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);
        model.addAttribute("total", total);
        model.addAttribute("users", userService.getUserListBy(pageDTO));
        model.addAttribute("pagination", pageDTO);
        model.addAttribute("searchDTO", searchDTO);

        return "admin/user";
    }


    /*======== 검색 + 페이징 처리 ========*/
    @GetMapping("user/search")
    public String userSearch(Criteria criteria, String search, String condition, Model model) {

        /* 한 페이지에 보여줄 게시글 개수 */
        int amount = 5;
        /* 검색된 결과의 총 개수 */
        int total = 0;

//        String type = null;
        /* 추후에 setUserId 세션으로 변경 */
        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setTypes(new ArrayList<>(Arrays.asList(condition)));
        searchDTO.setUserName(search);
        searchDTO.setUserAddress(search);
        PageDTO pageDTO = null;
//         페이지 번호가 없을 때, 디폴트 1페이지
        if (criteria.getPage() == 0) {
            criteria.create(1, amount);
        } else criteria.create(criteria.getPage(), amount);

        total= userService.findTotalBy(searchDTO);
        pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);
        model.addAttribute("total", total);
        model.addAttribute("users", userService.getUserListBy(pageDTO));
        model.addAttribute("pagination", pageDTO);
        model.addAttribute("searchDTO", searchDTO);
        model.addAttribute("condition", condition);
        model.addAttribute("search", search);
        return "admin/user";
    }

    /*======================== 문의 관리 ==========================*/
    /*======== 문의 목록 조회, 페이징 처리 ========*/
    @GetMapping("enquiry")
        public String enquiry(Criteria criteria, Model model) {

        int amount = 5;
        int total = 0;
        int getAnswerTrue = 0;
        int getAnswerFalse = 0;

        SearchDTO searchDTO = new SearchDTO();
        PageDTO pageDTO = null;

        if (criteria.getPage() == 0) {
            criteria.create(1, amount);
        } else criteria.create(criteria.getPage(), amount);

        // 답변대기 총 개수
        getAnswerFalse = inquiryService.getAnswerFalse();
        // 답변완료 총 개수
        getAnswerTrue = inquiryService.getAnswerTrue();

        total = inquiryService.getTotalByCount(searchDTO);
        pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);

        model.addAttribute("total", total);
        model.addAttribute("getAnswerTrue", getAnswerTrue);
        model.addAttribute("getAnswerFalse", getAnswerFalse);
        model.addAttribute("inquiries", inquiryService.getDTOListBy(pageDTO));
        model.addAttribute("pagination", pageDTO);
        model.addAttribute("searchDTO", searchDTO);
        return "admin/enquiry";
    }


    /*======== 검색 조건, 페이징 처리 ========*/
    @GetMapping("enquiry/search")
    public String enquirySearch(Criteria criteria, String search, String condition, Model model) {

        /* 한 페이지에 보여줄 게시글 개수 */
        int amount = 5;
        /* 검색된 결과의 총 개수 */
        int total = 0;
        int getAnswerTrue = 0;
        int getAnswerFalse = 0;

        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setTypes(new ArrayList<>(Arrays.asList(condition)));
        searchDTO.setUserName(search);
        searchDTO.setUserIdentification(search);
        searchDTO.setInquiryTitle(search);
        searchDTO.setInquiryContent(search);
        PageDTO pageDTO = null;
//         페이지 번호가 없을 때, 디폴트 1페이지
        if (criteria.getPage() == 0) {
            criteria.create(1, amount);
        } else criteria.create(criteria.getPage(), amount);

        // 답변대기 총 개수
        getAnswerFalse = inquiryService.getAnswerFalse();
        // 답변완료 총 개수
        getAnswerTrue = inquiryService.getAnswerTrue();

        total= inquiryService.getTotalByCount(searchDTO);

        pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);
        log.info(inquiryService.getDTOListBy(pageDTO).toString());
        model.addAttribute("total", total);
        model.addAttribute("getAnswerTrue", getAnswerTrue);
        model.addAttribute("getAnswerFalse", getAnswerFalse);
        model.addAttribute("inquiries", inquiryService.getDTOListBy(pageDTO));
        model.addAttribute("pagination", pageDTO);
        model.addAttribute("searchDTO", searchDTO);
        model.addAttribute("condition", condition);
        model.addAttribute("search", search);
        return "admin/enquiry";
    }


    /*======================== 공지사항 관리 ==========================*/
    /*======== 공지사항 목록 조회 ========*/
    @GetMapping("notice")
    public String notice(Criteria criteria, Model model) {
        int amount = 5;
        int total = 0;

        SearchDTO searchDTO = new SearchDTO();

        PageDTO pageDTO = null;

        if(criteria.getPage() == 0){
            criteria.create(1, amount);
        }else criteria.create(criteria.getPage(), amount);

        total= noticeService.getTotalBy(searchDTO);
        pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);
        model.addAttribute("total", total);
        model.addAttribute("notices", noticeService.getListBy(pageDTO));
        model.addAttribute("pagination", pageDTO);
        model.addAttribute("searchDTO", searchDTO);

        return "admin/notice";
    }

    /*======== 검색 조건, 페이징 처리 ========*/
    @GetMapping("notice/search")
    public String noticeSearch(Criteria criteria, String search, String condition, Model model) {
        /* 한 페이지에 보여줄 게시글 개수 */
        int amount = 5;
        /* 검색된 결과의 총 개수 */
        int total = 0;
//        String type = null;
        /* 추후에 setUserId 세션으로 변경 */
        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setTypes(new ArrayList<>(Arrays.asList(condition)));
        searchDTO.setNoticeTitle(search);
        searchDTO.setNoticeWriter(search);
        PageDTO pageDTO = null;
//         페이지 번호가 없을 때, 디폴트 1페이지
        if (criteria.getPage() == 0) {
            criteria.create(1, amount);
        } else criteria.create(criteria.getPage(), amount);

        total= noticeService.getTotalBy(searchDTO);
        pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);
        model.addAttribute("total", total);
        model.addAttribute("notices", noticeService.getListBy(pageDTO));
        model.addAttribute("pagination", pageDTO);
        model.addAttribute("searchDTO", searchDTO);
        model.addAttribute("condition", condition);
        model.addAttribute("search", search);
        return "admin/notice";
    }

    /*======== 공지사항 수정 ========*/
    @PostMapping("notice/update")
    public RedirectView setNotice(NoticeVO noticeVO){
        noticeService.setNotice(noticeVO);
        return new RedirectView("/admin/notice");
    }
    /*======== 공지사항 작성 ========*/
    @PostMapping("notice/register")
    public RedirectView registerNotice(NoticeVO noticeVO) {
        noticeService.registerNotice(noticeVO);
        return new RedirectView("/admin/notice");
    }

    /*======================== 결제사항 관리 ==========================*/
    /*======== 결제 목록 조회 ========*/
    @GetMapping("payment")
    public String payment(Criteria criteria, Model model) {
        int amount = 5;
        int total = 0;
        int payAmount = 0;
        SearchDTO searchDTO = new SearchDTO();

        PageDTO pageDTO = null;

        if (criteria.getPage() == 0) {
            criteria.create(1, amount);
        } else criteria.create(criteria.getPage(), amount);

        total = paymentService.getTotalPayCount(searchDTO);
        payAmount = paymentService.getTotalPay();

        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedNumber = decimalFormat.format(payAmount);

        pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);
        model.addAttribute("total", total);
        model.addAttribute("payAmount", formattedNumber);
        model.addAttribute("payments", paymentService.getList(pageDTO));
        model.addAttribute("pagination", pageDTO);
        model.addAttribute("searchDTO", searchDTO);

        return "admin/payment";
    }

    /*======== 검색 조건, 페이징 처리 ========*/
    @GetMapping("payment/search")
    public String paymentSearch(Criteria criteria, String search, String condition, Model model) {
        int amount = 5;
        int total = 0;
        int payAmount = 0;

        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setTypes(new ArrayList<>(Arrays.asList(condition)));
        searchDTO.setStorageAddress(search);
        searchDTO.setStorageSize(search);

        PageDTO pageDTO = null;

        if (criteria.getPage() == 0) {
            criteria.create(1, amount);
        } else criteria.create(criteria.getPage(), amount);

        total = paymentService.getTotalPayCount(searchDTO);
        payAmount = paymentService.getTotalPay();
        pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);
        model.addAttribute("total", total);
        model.addAttribute("payAmount", payAmount);
        model.addAttribute("payments", paymentService.getList(pageDTO));
        model.addAttribute("pagination", pageDTO);
        model.addAttribute("searchDTO", searchDTO);
        model.addAttribute("condition", condition);
        model.addAttribute("search", search);

        return "admin/payment";
    }

    /*======================== 리뷰 관리 ==========================*/
    /*======== 리뷰 목록 조회 ========*/
    @GetMapping("review")
    public String review(Criteria criteria, Model model) {
        int amount = 5;
        int total = 0;

        SearchDTO searchDTO = new SearchDTO();

        PageDTO pageDTO = null;

        if (criteria.getPage() == 0) {
            criteria.create(1, amount);
        } else criteria.create(criteria.getPage(), amount);

        total = reviewService.getTotalBy(searchDTO);
        pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);
        model.addAttribute("total", total);
        model.addAttribute("reviews", reviewService.getListBy(pageDTO));
        model.addAttribute("pagination", pageDTO);
        model.addAttribute("searchDTO", searchDTO);

        return "admin/review";
    }

    /*======== 검색 조건, 페이징 처리 ========*/
    @GetMapping("review/search")
    public String review(Criteria criteria, String search, String condition, Model model) {
        int amount = 5;
        int total = 0;

        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setTypes(new ArrayList<>(Arrays.asList(condition)));
        searchDTO.setReviewContext(search);
        searchDTO.setReviewTitle(search);

        PageDTO pageDTO = null;

        if (criteria.getPage() == 0) {
            criteria.create(1, amount);
        } else criteria.create(criteria.getPage(), amount);

        total = reviewService.getTotalBy(searchDTO);
        pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);
        model.addAttribute("total", total);
        model.addAttribute("reviews", reviewService.getListBy(pageDTO));
        model.addAttribute("pagination", pageDTO);
        model.addAttribute("condition", condition);
        model.addAttribute("search", search);
        model.addAttribute("searchDTO", searchDTO);

        return "admin/review";
    }
    /*======== 리뷰 수정 ========*/
    @PostMapping("review/update")
        public RedirectView setReview(ReviewVO reviewVO) {
            reviewService.registerSet(reviewVO);

        return new RedirectView("/admin/review");
    }

    /*======================== 창고 관리 ==========================*/
    /*======== 창고 목록 조회 ========*/
    @GetMapping("storage")
    public String storage(Criteria criteria, Model model) {

        int amount = 5;
        int total = 0;

        SearchDTO searchDTO = new SearchDTO();
//        searchDTO.setTypes(new ArrayList<>(Arrays.asList("userId")));
//        searchDTO.setUserId(2L);


        PageDTO pageDTO = null;

        if (criteria.getPage() == 0) {
            criteria.create(1, amount);
        } else criteria.create(criteria.getPage(), amount);

        total =  storageService.getTotalDTOBy(searchDTO);
        pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);
        model.addAttribute("total", total);
        model.addAttribute("storages", storageService.getStorageList(pageDTO));
        model.addAttribute("pagination", pageDTO);
        model.addAttribute("searchDTO", searchDTO);

        return "admin/storage";
    }

    /*======== 창고 수정 ========*/
    @PostMapping("storage/update")
    @ResponseBody
    public RedirectView updateStorage(@RequestParam("storageId") Long storageId,
                                      @RequestParam("storageAddress") String storageAddress,
                                      @RequestParam("storageTitle") String storageTitle,
                                      @RequestParam("userId") Long userId,
                                      @RequestParam("userName") String userName,
                                      @RequestParam("storagePhone") String storagePhone,
                                      @RequestParam("storageEndDate") String storageEndDate,
                                      @RequestParam("storagePrice") Integer storagePrice,
                                      @RequestParam("storageAddressDetail") String storageAddressDetail,
                                      @RequestParam("storageUseDate") String storageUseDate){

        StorageVO storageVO = storageService.getStorage(storageId);
        storageVO.setStorageTitle(storageTitle);
        storageVO.setStorageAddress(storageAddress);
        storageVO.setStoragePrice(storagePrice);
        storageVO.setStorageEndDate(storageEndDate);
        storageVO.setStorageUseDate(storageUseDate);
        storageVO.setStoragePhone(storagePhone);
        storageVO.setStorageAddressDetail(storageAddressDetail);
        storageService.modify(storageVO);
        UserVO userVO = userService.getUser(userId);
        userVO.setUserName(userName);
        userService.modify(userVO);

        return new RedirectView("/admin/storage");
    }

    /*======== 검색 조건, 페이징 처리 ========*/
    @GetMapping("storage/search")
    public String storageSearch(Criteria criteria, String search, String condition, Model model) {
        /* 한 페이지에 보여줄 게시글 개수 */
        int amount = 5;
        /* 검색된 결과의 총 개수 */
        int total = 0;
//        String type = null;
        /* 추후에 setUserId 세션으로 변경 */
        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setTypes(new ArrayList<>(Arrays.asList(condition)));
        searchDTO.setStorageAddress(search);
        searchDTO.setStorageSize(search);
        PageDTO pageDTO = null;
//         페이지 번호가 없을 때, 디폴트 1페이지
        if (criteria.getPage() == 0) {
            criteria.create(1, amount);
        } else criteria.create(criteria.getPage(), amount);

        total= storageService.getTotalDTOBy(searchDTO);
        pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);
//        List<UserVO> users =  userService.getUserListBy(pageDTO);
        model.addAttribute("total", total);
        model.addAttribute("storages", storageService.getStorageList(pageDTO));
        model.addAttribute("pagination", pageDTO);
        model.addAttribute("searchDTO", searchDTO);
        model.addAttribute("condition", condition);
        model.addAttribute("search", search);
        return "admin/storage";
    }
}


