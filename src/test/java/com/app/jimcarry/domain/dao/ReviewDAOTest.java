//package com.app.jimcarry.domain.dao;
//
//import com.app.jimcarry.domain.dto.PageDTO;
//import com.app.jimcarry.domain.dto.ReviewDTO;
//import com.app.jimcarry.domain.dto.SearchDTO;
//import com.app.jimcarry.domain.vo.Criteria;
//import com.app.jimcarry.domain.vo.ReviewVO;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Arrays;
//import java.util.List;
//
//@SpringBootTest
//@Slf4j
//@Transactional
//public class ReviewDAOTest {
//
//    @Autowired
//    ReviewDAO reviewDAO;
//
//    @Autowired
//    ReviewVO reviewVO;
//
//    @Autowired
//    PageDTO pageDTO;
//
//    /* 리뷰 목록 조회 */
//    @Test
//    public void findAll(PageDTO pageDTO) {
//        log.info(reviewDAO.findAll(pageDTO).toString());
//    }
//
//    /* 한 창고의 리뷰 조회 */
//    @Test
//    public void findByStorageId() {
//        reviewDAO.findByStorageId(8L);
//    }
//
//    /* 한 창고의 리뷰 개수 조회*/
//    @Test
//    public void getTotalByStorageId() {
//        reviewDAO.getTotalById(1L);
//    }
//
//    /* 리뷰 조건조회 */
//    @Test
//    public void findAllBy() {
//        /*SearchDTO searchDTO = new SearchDTO().createTypes(Arrays.asList("userId"));*/
//        SearchDTO searchDTO = new SearchDTO().createTypes(Arrays.asList("storageId"));
//        /*searchDTO.setUserId(2L);*/
//        searchDTO.setStorageId(1L);
//        Criteria criteria = new Criteria().create(1, 5);
//        int total = reviewDAO.getTotalBy(searchDTO);
//
//        reviewDAO.findAllBy(new PageDTO().createPageDTO(criteria, total, searchDTO));
//    }
//
//    /* 리뷰 조건조회 개수 */
//    @Test
//    public void getTotalBy() {
//        SearchDTO searchDTO = new SearchDTO().createTypes(Arrays.asList("userId"));
//        searchDTO.setUserId(2L);
//        reviewDAO.getTotalBy(searchDTO);
//    }
//}