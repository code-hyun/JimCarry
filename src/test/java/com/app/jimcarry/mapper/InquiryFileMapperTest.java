//package com.app.jimcarry.mapper;
//
//import com.app.jimcarry.domain.vo.FileVO;
//import com.app.jimcarry.domain.vo.InquiryFileVO;
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
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Slf4j
//@Transactional
//public class InquiryFileMapperTest {
//    @Autowired
//    InquiryFileMapper inquiryFileMapper;
//    @Autowired
//    InquiryFileVO inquiryFileVO;
//
//    @BeforeEach
//    void setInquiryFileVO() {
//        inquiryFileVO.setInquiryId(1L);
//        inquiryFileVO.setFileOrgName("테스트1.png");
//        /* ABSOLUTE_PATH = "C:/upload" */
//        inquiryFileVO.setFilePath(FileVO.ABSOLUTE_PATH + "/" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
//        inquiryFileVO.setFileUuid(UUID.randomUUID().toString());
//    }
//
//    @Test
//    void insert() {
//        inquiryFileMapper.insert(inquiryFileVO);
//    }
//
//    @Test
//    void selectAll() {
//        inquiryFileMapper.insert(inquiryFileVO);
//        assertThat(inquiryFileMapper.selectAll(1L).get(0).getFileOrgName()).isEqualTo("테스트1.png");
//    }
//
//    @Test
//    void delete() {
//        inquiryFileMapper.delete(1L);
//        assertThat(inquiryFileMapper.selectAll(1L).size()).isEqualTo(0);
//    }
//}
