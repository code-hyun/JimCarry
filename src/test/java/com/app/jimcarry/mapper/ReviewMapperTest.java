//package com.app.jimcarry.mapper;
//
//
//import com.app.jimcarry.domain.dto.PageDTO;
//import com.app.jimcarry.domain.dto.ReviewDTO;
//import com.app.jimcarry.domain.dto.SearchDTO;
//import com.app.jimcarry.domain.vo.Criteria;
//import com.app.jimcarry.domain.vo.ReviewVO;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Arrays;
//
//@SpringBootTest
//@Slf4j
//@Transactional
//public class ReviewMapperTest {
//    @Autowired
//    PageDTO pageDTO;
//
//    @Autowired
//    ReviewDTO reviewDTO;
//
//    @Autowired
//    ReviewVO reviewVO;
//
//    @Autowired
//    ReviewMapper reviewMapper;
//
//    @BeforeEach
//    void setReviewVO(){
//        reviewVO.setUserId(2L);
//        reviewVO.setStorageId(1L);
//        reviewVO.setReviewTitle("리뷰매퍼테스트");
//        reviewVO.setReviewContext("리뷰매퍼테스내용");
//    }
//
//    /* 리뷰 추가 */
//    @Test
//    public void insert() {
//        ReviewVO reviewVO = new ReviewVO();
//        reviewVO.setUserId(6L);
//        reviewVO.setStorageId(8L);
//        reviewVO.setReviewTitle("창고리뷰 제목 2");
//        reviewVO.setReviewContext("창고리뷰 내용 2");
//        reviewMapper.insert(reviewVO);
//    }
//
//    /*리뷰 전체 목록 조회*/
//    @Test
//    public void getList() {
//        Criteria criteria = new Criteria().create(1, 10);
//        SearchDTO searchDTO = new SearchDTO();
//        searchDTO.setDesc(true);
//        reviewMapper.selectAll(new PageDTO().createPageDTO(criteria, reviewMapper.total(), searchDTO));
//    }
//
//    /* 리뷰 조회*/
//    @Test
//    public void select(){
//        reviewMapper.selectByStorageId(8L);
//    }
//
//    @Test
//    public void totalByStorageId(){
//        reviewMapper.totalById(8L);
//    }
//
//    @Test
//    void total() {
//    }
//
//    @Test
//    void selectAllBy() {
//        int total = 0;
//        SearchDTO searchDTO = new SearchDTO().createTypes(Arrays.asList("storageId"));
//        Criteria criteria = new Criteria().create(1, 5);
//        searchDTO.setStorageId(8L);
//        total = reviewMapper.totalBy(searchDTO);
//        reviewMapper.selectAllBy(new PageDTO().createPageDTO(criteria, total, searchDTO));
//    }
//
//    @Test
//    void totalBy() {
//        SearchDTO searchDTO = new SearchDTO().createTypes(Arrays.asList("userId"));
//        searchDTO.setUserId(1L);
//        reviewMapper.totalBy(searchDTO);
//    }
//
//    @Test
//    void update() {
//    }
//}
