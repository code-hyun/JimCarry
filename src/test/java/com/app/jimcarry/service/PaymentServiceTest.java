//package com.app.jimcarry.service;
//
//import com.app.jimcarry.domain.dao.PaymentDAO;
//import com.app.jimcarry.domain.dto.PageDTO;
//import com.app.jimcarry.domain.dto.PaymentDTO;
//import com.app.jimcarry.domain.dto.SearchDTO;
//import com.app.jimcarry.domain.vo.Criteria;
//import com.app.jimcarry.domain.vo.PaymentVO;
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
//public class PaymentServiceTest {
//    @Autowired
//    private PaymentService paymentService;
//    @Autowired
//    private PaymentVO paymentVO;
//    @Autowired
//    private PaymentDTO paymentDTO;
//
//    @BeforeEach
//    void setPaymentVO() {
//        paymentVO.setUserId(2L);
//        paymentVO.setStorageId(1L);
//        paymentVO.setPaymentAmount(120000);
//    }
//
//    @Test
//    void register() {
//    }
//
//    @Test
//    void getPayment() {
//    }
//
//    @Test
//    void getList() {
//    }
//
//    @Test
//    void getListBy() {
//        paymentService.register(paymentVO);
//
//        Criteria criteria = new Criteria().create(1, 5);
//        SearchDTO searchDTO = new SearchDTO().createTypes(Arrays.asList("userId"));
//        searchDTO.setUserId(2L);
//        int total = paymentService.getTotalBy(searchDTO);
//        paymentService.getListBy(new PageDTO().createPageDTO(criteria, total, searchDTO));
//    }
//
//    @Test
//    void geTotalBy() {
//        paymentService.register(paymentVO);
//        SearchDTO searchDTO = new SearchDTO().createTypes(Arrays.asList("userId"));
//        searchDTO.setUserId(2L);
//        paymentService.getTotalBy(searchDTO);
//    }
//
//    /*유저 정보 조회*/
//    @Test
//    public void getUser(){
//        paymentService.getUser(1L);
//    }
//
//    /*창고 정보 조회*/
//    @Test
//    public void getStorage(){
//        paymentService.getStorage(paymentDTO);
//    }
//
////    @Test
////    public void findInfo(){
////        paymentService.findInfo(paymentDTO);
////    }
//}
