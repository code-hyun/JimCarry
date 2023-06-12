//package com.app.jimcarry.mapper;
//
//
//import com.app.jimcarry.domain.dto.InquiryDTO;
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
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@Slf4j
//@Transactional
//public class InquiryMapperTest {
//
//    @Autowired
//    private InquiryMapper inquiryMapper;
//    @Autowired
//    private InquiryVO inquiryVO;
//    @Autowired
//    private InquiryDTO inquiryDTO;
//
//    @BeforeEach
//    void setInsertVO() {
//        inquiryVO.setUserId(1L);
//        inquiryVO.setInquiryTitle("inquiryMapperTest");
//        inquiryVO.setInquiryContent("inquiryMapperTest");
//    }
//
//    //    추가
//    @Test
//    void insert() {
//        inquiryMapper.insert(inquiryVO);
//    }
//
//    //    조회
//    @Test
//    void select() {
//        inquiryMapper.insert(inquiryVO);
//        inquiryMapper.select(inquiryVO.getInquiryId());
//    }
//
//    //    전체조회
//    void selectAll() {
//    }
//
//    //    조건조회
//    @Test
//    public void selectBy() {
//        int total = 0;
//        Criteria criteria = new Criteria().create(1, 10);
//        SearchDTO searchDTO = new SearchDTO().createTypes(new ArrayList<>(Arrays.asList("userId")));
//        searchDTO.setUserId(2L);
//        total = inquiryMapper.totalBy(searchDTO);
//        inquiryMapper.selectBy(new PageDTO().createPageDTO(criteria, total, searchDTO));
//    }
//
//    //    조건조회 개수
//    @Test
//    public void totalBy() {
//
//    }
//
//    @Test
//    void update() {
//        inquiryMapper.insert(inquiryVO);
//        inquiryVO.setInquiryTitle("updated");
//        inquiryMapper.update(inquiryVO);
//        assertThat(inquiryMapper.select(inquiryVO.getInquiryId()).getInquiryTitle()).isEqualTo("updated");
//    }
//}
