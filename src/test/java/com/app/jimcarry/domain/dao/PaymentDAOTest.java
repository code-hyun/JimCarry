//package com.app.jimcarry.domain.dao;
//
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
//public class PaymentDAOTest {
//
//    @Autowired
//    PaymentDAO paymentDAO;
//    @Autowired
//    PaymentVO paymentVO;
//    @Autowired
//    PaymentDTO paymentDTO;
//
//    @BeforeEach
//    void setPaymentVO() {
//        paymentVO.setUserId(2L);
//        paymentVO.setStorageId(1L);
//        paymentVO.setPaymentAmount(120000);
//    }
//
//    @Test
//    void findListBy() {
//        paymentDAO.save(paymentVO);
//
//        Criteria criteria = new Criteria().create(1, 5);
//        SearchDTO searchDTO = new SearchDTO().createTypes(Arrays.asList("userId"));
//        searchDTO.setUserId(2L);
//        int total = paymentDAO.findTotalBy(searchDTO);
//        paymentDAO.findAllBy(new PageDTO().createPageDTO(criteria, total, searchDTO));
//    }
//
//    @Test
//    void findTotalBy() {
//        paymentDAO.save(paymentVO);
//        SearchDTO searchDTO = new SearchDTO().createTypes(Arrays.asList("userId"));
//        searchDTO.setUserId(2L);
//        paymentDAO.findTotalBy(searchDTO);
//    }
//
//    /*유저 정보 조회*/
//    @Test
//    public void findUserInfo(){
//        paymentDAO.findUserInfo(1L);
//    }
//
//    @Test
//    public void findInfo(){paymentDAO.findStorageInfo(paymentDTO);}
//}
