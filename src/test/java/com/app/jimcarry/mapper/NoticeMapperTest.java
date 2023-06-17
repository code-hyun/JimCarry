//package com.app.jimcarry.mapper;
//
//
//import com.app.jimcarry.domain.dto.PageDTO;
//import com.app.jimcarry.domain.dto.SearchDTO;
//import com.app.jimcarry.domain.vo.Criteria;
//import com.app.jimcarry.domain.vo.NoticeVO;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//@SpringBootTest
//@Slf4j
//@Transactional
//public class NoticeMapperTest {
//    @Autowired
//    PageDTO pageDTO;
//
//    @Autowired
//    NoticeVO noticeVO;
//
//    @Autowired
//    NoticeMapper noticeMapper;
//
//    @Test
//    public void getListTest(PageDTO pageDTO){
//        Criteria criteria = new Criteria().create(1, 10);
//        SearchDTO searchDTO = new SearchDTO();
//        searchDTO.setDesc(true);
//        noticeMapper.selectAll(new PageDTO().createPageDTO(criteria, noticeMapper.total(), searchDTO));
//
//
//    }
//
//
//}
