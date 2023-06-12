//package com.app.jimcarry.mapper;
//
//import com.app.jimcarry.domain.dto.PageDTO;
//import com.app.jimcarry.domain.dto.PaymentDTO;
//import com.app.jimcarry.domain.dto.SearchDTO;
//import com.app.jimcarry.domain.vo.Criteria;
//import com.app.jimcarry.domain.vo.PaymentVO;
//import com.app.jimcarry.domain.vo.UserVO;
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
//public class PaymentMapperTest {
//    @Autowired
//    PaymentMapper paymentMapper;
//    @Autowired
//    PaymentVO paymentVO;
//    @Autowired
//    PaymentDTO paymentDTO;
//
//    @BeforeEach
//    void setPaymentVO(){
//        paymentVO.setUserId(2L);
//        paymentVO.setStorageId(1L);
//        paymentVO.setPaymentAmount(120000);
//    }
//
//    @Test
//    void selectAllBy() {
//        paymentMapper.insert(paymentVO);
//
//        Criteria criteria = new Criteria().create(1, 5);
//        SearchDTO searchDTO = new SearchDTO().createTypes(Arrays.asList("userId"));
//        searchDTO.setUserId(2L);
//        int total = paymentMapper.totalBy(searchDTO);
//        paymentMapper.selectAllBy(new PageDTO().createPageDTO(criteria, total, searchDTO));
//    }
//
//    @Test
//    void totalBy() {
//        paymentMapper.insert(paymentVO);
//        SearchDTO searchDTO = new SearchDTO().createTypes(Arrays.asList("userId"));
//        searchDTO.setUserId(2L);
//        paymentMapper.totalBy(searchDTO);
//    }
//
//    @Test
//    void selectAllPayment(){
//        Criteria criteria = new Criteria().create(1, 5);
//        SearchDTO searchDTO = new SearchDTO();
//        int total = 5;
//        paymentMapper.selectAllPayment(new PageDTO().createPageDTO(criteria, total, searchDTO));
//    }
//
//    /*유저 정보 조회*/
//    @Test
//    public void selectUser(){
//        paymentMapper.selectUser(1L);
//    }
//
//    /*창고 정보 조회*/
//    @Test
//    public void selectStorage(){
//        PaymentDTO paymentDTO = new PaymentDTO();
//        paymentMapper.selectStorage(new PaymentDTO());}
//
//
//
//    @Test
//    public void selectPayment(){
//        paymentMapper.selectPayment(1L);
//    }
//}
