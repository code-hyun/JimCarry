//package com.app.jimcarry.service;
//
//import com.app.jimcarry.domain.dao.InquiryDAO;
//import com.app.jimcarry.domain.dto.PageDTO;
//import com.app.jimcarry.domain.dto.SearchDTO;
//import com.app.jimcarry.domain.vo.Criteria;
//import com.app.jimcarry.domain.vo.InquiryVO;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//
//@SpringBootTest
//@Slf4j
//@Transactional
//public class InquiryServiceTest {
//
//    @Autowired
//    private InquiryService inquiryService;
//    @Autowired
//    private InquiryVO inquiryVO;
//
//    @BeforeEach
//    void setInsertVO() {
//        inquiryVO.setUserId(1L);
//        inquiryVO.setInquiryTitle("inquiryMapperTest");
//        inquiryVO.setInquiryContent("inquiryMapperTest");
//       /* inquiryVO.setInquiryRegist(current_stamp); */
//    }
//
////    //    추가
////    @Test
////    public void register() {
////            inquiryService.register(inquiryVO);
////    }
//
//    //    조회
//    @Test
//    public void getInquiry() {
//
//    }
//
//    //    전체조회
//
////    전체조회 개수
//
//    //    조건조회
//    @Test
//    public void getListBy() {
//        int total = 0;
//        Criteria criteria = new Criteria().create(2, 10);
//        SearchDTO searchDTO = new SearchDTO().createTypes(new ArrayList<>(Arrays.asList("userId")));
//        searchDTO.setUserId(2L);
//        total = inquiryService.getTotalBy(searchDTO);
//        inquiryService.getListBy(new PageDTO().createPageDTO(criteria, total, searchDTO));
//    }
//
//    //    조건조회 개수
//    @Test
//    public void getTotalBy() {
//        SearchDTO searchDTO = new SearchDTO().createTypes(new ArrayList<>(Arrays.asList("userId")));
//        searchDTO.setUserId(2L);
//        inquiryService.getTotalBy(searchDTO);
//    }
//
////    //    수정
////    @Test
////    void updateInquiry() {
////        inquiryService.register(inquiryVO);
////        inquiryVO.setInquiryTitle("updated");
////        inquiryService.updateInquiry(inquiryVO);
////        assertThat(inquiryService.getInquiry(inquiryVO.getInquiryId()).getInquiryTitle()).isEqualTo("updated");
////    }
//
////    삭제
//}
