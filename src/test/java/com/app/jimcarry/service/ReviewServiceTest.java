//package com.app.jimcarry.service;
//
//import com.app.jimcarry.domain.dto.PageDTO;
//import com.app.jimcarry.domain.dto.SearchDTO;
//import com.app.jimcarry.domain.vo.Criteria;
//import com.app.jimcarry.mapper.ReviewMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//@SpringBootTest
//@Slf4j
//public class ReviewServiceTest {
//
//    @Autowired
//    ReviewService reviewService;
//
//    @Autowired
//    ReviewMapper reviewMapper;
//
//    @Autowired
//    PageDTO pageDTO;
//
//    /*리뷰목록조회*/
//    @Test
//    void getList() {
//        int total = 0;
//        Criteria criteria = new Criteria().create(1, 10);
//        PageDTO pageDTO = null;
//        SearchDTO searchDTO = new SearchDTO();
//
//        total = reviewService.getTotal();
//        reviewService.getList(new PageDTO().createPageDTO(criteria, total, searchDTO));
//    }
//
//    /* 한 창고의 리뷰 조회 */
//    @Test
//    public void getListByStorageId() {
//        reviewService.getListByStorageId(8L);
//    }
//
//    /* 한 창고의 리뷰 개수 조회*/
//    @Test
//    public void getTotalByStorageId() {
//        reviewService.getTotalById(1L);
//    }
//
//    /* 리뷰 조건조회 */
//    @Test
//    public void getListBy() {
//        /*SearchDTO searchDTO = new SearchDTO().createTypes(Arrays.asList("userId"));*/
//        SearchDTO searchDTO = new SearchDTO().createTypes(Arrays.asList("storageId"));
//        /*searchDTO.setUserId(2L);*/
//        searchDTO.setStorageId(1L);
//        Criteria criteria = new Criteria().create(1, 5);
//        int total = reviewService.getTotalBy(searchDTO);
//
//        reviewService.getListBy(new PageDTO().createPageDTO(criteria, total, searchDTO));
//    }
//
//    /* 리뷰 조건조회 개수 */
//    @Test
//    public void getTotalBy() {
//        SearchDTO searchDTO = new SearchDTO().createTypes(Arrays.asList("userId"));
//        searchDTO.setUserId(2L);
//        reviewService.getTotalBy(searchDTO);
//    }
//}
