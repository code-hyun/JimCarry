//package com.app.jimcarry.domain.dao;
//
//import com.app.jimcarry.domain.dto.PageDTO;
//import com.app.jimcarry.domain.dto.SearchDTO;
//import com.app.jimcarry.domain.vo.Criteria;
//import com.app.jimcarry.domain.vo.FileVO;
//import com.app.jimcarry.domain.vo.InquiryFileVO;
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
//
//@SpringBootTest
//@Slf4j
//@Transactional
//public class InquiryDAOTest {
//
//    @Autowired
//    InquiryDAO inquiryDAO;
//    @Autowired
//    InquiryVO inquiryVO;
//    @Autowired
//    InquiryFileVO inquiryFileVO;
//    @Autowired
//    FileVO fileVO;
//
//    @BeforeEach
//    void setInquiryVO() {
//        inquiryVO.setUserId(1L);
//        inquiryVO.setInquiryTitle("inquiryDAOTest");
//        inquiryVO.setInquiryContent("inquiryDAOTest");
//    }
//
//    //    추가
//    @Test
//    void save() {
//        inquiryDAO.save(inquiryVO);
//    }
//
//    //    조회
//    @Test
//    void findById() {
//    }
//
//    //    전체조회
//
////    전체조회 개수
//
//    //    조건조회
//    @Test
//    public void findListBy(){
//        int total = 0;
//        Criteria criteria = new Criteria().create(1, 10);
//        SearchDTO searchDTO = new SearchDTO().createTypes(new ArrayList<>(Arrays.asList("userId")));
//        searchDTO.setUserId(2L);
//        total = inquiryDAO.findTotalBy(searchDTO);
//        inquiryDAO.findListBy(new PageDTO().createPageDTO(criteria, total, searchDTO));
//    }
//
//    //    조건조회 개수
//    @Test
//    public void findTotal(){
//        SearchDTO searchDTO = new SearchDTO().createTypes(new ArrayList<>(Arrays.asList("userId")));
//        searchDTO.setUserId(2L);
//        inquiryDAO.findTotalBy(searchDTO);
//    }
//
//    @Test
//    void testSetInquiryVO() {
//        inquiryDAO.save(inquiryVO);
//        inquiryVO.setInquiryTitle("updated");
//        inquiryDAO.setInquiryVO(inquiryVO);
//        assertThat(inquiryDAO.findById(inquiryVO.getInquiryId()).getInquiryTitle()).isEqualTo("updated");
//    }
//}
