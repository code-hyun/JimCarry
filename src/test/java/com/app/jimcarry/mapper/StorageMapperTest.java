//package com.app.jimcarry.mapper;
//
//import com.app.jimcarry.domain.dto.PageDTO;
//import com.app.jimcarry.domain.dto.SearchDTO;
//import com.app.jimcarry.domain.dto.StorageDTO;
//import com.app.jimcarry.domain.vo.Criteria;
//import com.app.jimcarry.domain.vo.StorageVO;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.annotations.Param;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@Slf4j
//@Transactional
//public class StorageMapperTest {
//
//    @Autowired
//    private StorageMapper storageMapper;
//    @Autowired
//    private StorageVO storageVO;
//
//    @BeforeEach
//    void setStorageVO(){
//        storageVO.setUserId(1L);
//        storageVO.setStorageTitle("storageMapperTest");
//        storageVO.setStorageName("그래요");
//        storageVO.setStoragePhone("01022223333");
//        storageVO.setStorageSize("특대");
//        storageVO.setStoragePrice(30000);
//        storageVO.setStorageAddress("서울시 강남구 역삼동");
//        storageVO.setStorageAddressDetail("상세주소");
//        storageVO.setStorageUseDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
//        storageVO.setStorageEndDate("2099/12/31");
//    }
//
//    @Test
//    void insert() {
//        storageMapper.insert(storageVO);
//    }
//
//    @Test
//    void select() {
//        storageMapper.insert(storageVO);
//        assertThat(storageMapper.select(storageVO.getStorageId())).isNotNull();
//    }
//
//    @Test
//    void selectAll() {
//        int total = storageMapper.total();
//        Criteria criteria = new Criteria().create(1, 10);
//    }
//
//    @Test
//    void selectBy() {
//        SearchDTO searchDTO = new SearchDTO();
//        searchDTO.setTypes(new ArrayList<String>(Arrays.asList("storageAddress")));
//        searchDTO.setStorageAddress("경기");
//        int total = storageMapper.totalBy(searchDTO);
//        Criteria criteria = new Criteria().create(1, 10);
//        PageDTO pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);
//        storageMapper.selectBy(pageDTO);
//    }
//
//    @Test
//    void totalBy() {
//        SearchDTO searchDTO = new SearchDTO();
//        searchDTO.setTypes(new ArrayList<String>(Arrays.asList("userId")));
//        searchDTO.setUserId(2L);
//        storageMapper.totalBy(searchDTO);
//    }
//
//    //    storageDTO 조회
//    @Test
//    public void selectAllBy() {
//        storageMapper.selectAllBy(8L);
//    }
//
//    /*storageDTO 주소별 조회*/
//    @Test
//    public void selectByAddress() {
//        int total = 0;
//        SearchDTO searchDTO = new SearchDTO();
//        Criteria criteria = new Criteria().create(1, 5);
//        searchDTO.setTypes(new ArrayList<String>(Arrays.asList("storageAddress", "storageAddressNumber")));
//        searchDTO.setStorageAddress("경기");
//        searchDTO.setStorageAddressNumber(1);
//        total = storageMapper.totalBy(searchDTO);
//        storageMapper.selectDTOAllBy(new PageDTO().createPageDTO(criteria, total, searchDTO));
//    }
//
//    /*storageDTO 메인 신규창고 조회*/
//    @Test
//    public void selectMain(){
//        storageMapper.selectDTOAll();
//    }
//
//    /*storageDTO 메인 리뷰창고 조회*/
//    @Test
//    public void selectMainReview(){
//        storageMapper.selectStorageDTOAll();
//    }
//
//}
