//package com.app.jimcarry.mapper;
//
//
//import com.app.jimcarry.domain.vo.ReviewFileVO;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.UUID;
//
//@SpringBootTest
//@Slf4j
//@Transactional
//public class ReviewFileMapperTest {
//    @Autowired
//    ReviewFileMapper reviewFileMapper;
//    @Autowired
//    ReviewFileVO reviewFileVO;
//    @BeforeEach
//    void setReviewFileVO(){
//        reviewFileVO.setReviewId(1L);
//        reviewFileVO.setFileOrgName("테스트1.png");
//        reviewFileVO.setFilePath(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
//        reviewFileVO.setFileUuid(UUID.randomUUID().toString());
//    }
//
//    @Test
//    void insert() {
//        reviewFileMapper.insert(reviewFileVO);
//    }
//
//    @Test
//    void selectAll() {
//        reviewFileMapper.insert(reviewFileVO);
//        reviewFileMapper.selectAll(1L);
//    }
//
//    @Test
//    void delete() {
//        reviewFileMapper.insert(reviewFileVO);
//        reviewFileMapper.delete(reviewFileVO.getFileId());
//    }
//
//    /*@Test
//    public void selectByStorageId() {
//        reviewFileMapper.selectByStorageId(1L);
//    }*/
//}
