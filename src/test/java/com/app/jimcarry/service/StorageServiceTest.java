//package com.app.jimcarry.service;
//
//
//import com.app.jimcarry.domain.dto.PageDTO;
//import com.app.jimcarry.domain.dto.SearchDTO;
//import com.app.jimcarry.domain.dto.StorageDTO;
//import com.app.jimcarry.domain.vo.Criteria;
//import com.app.jimcarry.domain.vo.StorageVO;
//import lombok.extern.slf4j.Slf4j;
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
//@SpringBootTest
//@Slf4j
//@Transactional
//public class StorageServiceTest {
//
//    @Autowired
//    private StorageService storageService;
//    @Autowired
//    StorageVO storageVO;
//
//    @BeforeEach
//    void setStorageVO(){
//        storageVO.setUserId(1L);
//        storageVO.setStorageName("그래요");
//        storageVO.setStoragePhone("01022223333");
//        storageVO.setStorageTitle("storageMapperTest");
//        storageVO.setStorageSize("특대");
//        storageVO.setStoragePrice(30000);
//        storageVO.setStorageAddress("서울시 강남구 역삼동");
//        storageVO.setStorageAddressDetail("상세주소");
//        storageVO.setStorageUseDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
//        storageVO.setStorageEndDate("2099/12/31");
//    }
//
////    @Test
////    void register() {storageService.register(storageVO);
////    }
//
//    @Test
//    void getStorage() {
//    }
//
//    @Test
//    void getList() {
//    }
//
//    @Test
//    void getListBy() {
//        int total = 0;
//        Criteria criteria = new Criteria().create(1, 10);
//        PageDTO pageDTO = null;
//        SearchDTO searchDTO = new SearchDTO();
//        searchDTO.setTypes(new ArrayList<>(Arrays.asList("userId")));
//        searchDTO.setUserId(2L);
//
//        total = storageService.getTotalBy(searchDTO);
//        storageService.getListBy(new PageDTO().createPageDTO(criteria, total, searchDTO));
//    }
//
//    @Test
//    void getTotal() {
//    }
//
//    @Test
//    void getTotalBy() {
//        SearchDTO searchDTO = new SearchDTO();
//        searchDTO.setTypes(new ArrayList<>(Arrays.asList("storageAddressNumber")));
///*
//        searchDTO.setTypes(new ArrayList<>(Arrays.asList("userId")));
//*/
//        searchDTO.setStorageAddressNumber(6);
//        log.info("getTotalby : " + storageService.getTotalBy(searchDTO));
//    }
//
//
//    //    검색에 맞는 DTO 창고 조회
//    @Test
//    public void getStorageListBy(){
//        storageService.getStorageBy(8L);
//    }
//
//    /*DTO 창고 조건 조회*/
//    @Test
//    public void getStorageDTO(){
//        int total = 0;
//        Criteria criteria = new Criteria().create(1, 10);
//        PageDTO pageDTO = null;
//        SearchDTO searchDTO = new SearchDTO();
//        searchDTO.setTypes(new ArrayList<>(Arrays.asList("storageAddress")));
//        searchDTO.setStorageAddress("서울");
//
//        total = storageService.getTotalDTOBy(searchDTO);
//        storageService.getStorageDTOBy(new PageDTO().createPageDTO(criteria, total, searchDTO));
//    }
//
//    /*DTO 메인 신규창고 조회*/
//    @Test
//    public void getStorageDTOMain(){
//        storageService.getStorageDTO();
//    }
//
//    /*DTO 메인 리뷰창고 조회*/
//    @Test
//    public void getStorageDTOMains(){
//        storageService.getStorage();
//    }
//
//    @Test
//    public void getStorageDTOBy() {
//        int total = 0;
//        Criteria criteria = new Criteria().create(1, 10);
//        PageDTO pageDTO = null;
//        SearchDTO searchDTO = new SearchDTO();
//        searchDTO.setTypes(new ArrayList<>(Arrays.asList("storageAddress")));
//        searchDTO.setStorageAddress("경북");
//
//        total = storageService.getTotalDTOBy(searchDTO);
//        storageService.getStorageDTOBy(new PageDTO().createPageDTO(criteria, total, searchDTO));
//    }
//
//}
