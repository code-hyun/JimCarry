//package com.app.jimcarry.service;
//
//import com.app.jimcarry.domain.vo.MailTO;
//import com.app.jimcarry.domain.vo.UserVO;
//import lombok.extern.slf4j.Slf4j;
//import net.nurigo.java_sdk.exceptions.CoolsmsException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.*;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@SpringBootTest
//@Slf4j
//@Transactional
//class UserServiceTest {
//
//    @Autowired
//    UserService userService;
//    @Autowired
//    UserVO userVO;
//
//    @BeforeEach
//    void setUserVO() {
//        userVO.setUserIdentification("userMapperTest");
//        userVO.setUserPassword("1234");
//        userVO.setUserEmail("userMapperTest@gmail.com");
//        userVO.setUserPhone("01012341234");
//        userVO.setUserAddress("서울시 강남구 역삼동");
//        userVO.setUserAddressDetail("경리단길 123");
//        userVO.setUserName("테스트");
////        userVO.setUserGender("남"); // 선택안함
////        userVO.setUserGender("선택 안함"); // 선택안함
//        userVO.setUserBirth("1900-01-01");
//    }
//
//    @Test
//    void registerUser() {
//        assertDoesNotThrow(() -> {
//            userService.registerUser(userVO);
//        });
////        userVO.setUserIdentification("userServiceTest");
////        assertThrows(IllegalArgumentException.class, () -> {
////            userService.registerUser(userVO);
////        });
////        assertThrows(NoSuchElementException.class, () -> {
////            userService.registerUser(null);
////        });
//    }
//
//    @Test
//    void getUser() {
//        userService.registerUser(userVO);
//        final Long userId = userVO.getUserId();
//        assertThat(userService.getUser(userId).getUserId()).isEqualTo(userId);
//        userService.removeUser(userId);
//        assertThrows(NoSuchElementException.class, () -> userService.getUser(userId));
//    }
//
//    @Test
//    void getUserListBy() {
//
//    }
//
//    @Test
//    void getList() {
////        Criteria criteria = new Criteria().create(1, 10);
////        userService.registerUser(userVO);
////        assertThat(userService.getList(criteria).size()).isGreaterThan(0);
//    }
//
//    @Test
//    void updateUser() {
//        String update = "updateduserMapperTest";
//        String updatePassword = "4321";
//        userService.registerUser(userVO);
//        userVO.setUserIdentification(update);
//        userVO.setUserPassword(updatePassword);
//        userService.updateUser(userVO);
//        assertThat(userService.getUser(userVO.getUserId()).getUserIdentification()).isEqualTo(update);
//        log.info(userService.getUser(userVO.getUserId()).getUserPassword());
//    }
//
//    @Test
//    void removeUser() {
//        userService.registerUser(userVO);
//        Long userId = userVO.getUserId();
//        assertDoesNotThrow(() -> {
//            userService.removeUser(userId);
//        });
//        assertThrows(NoSuchElementException.class, () -> {
//            userService.removeUser(userId);
//        });
//    }
//
//    @Test
//    void checkEmailDuplicate() {
//        userService.registerUser(userVO);
//        assertThat(userService.checkEmailDuplicate(userVO.getUserEmail())).isFalse();
//    }
//
//    @Test
//    public void sendSMS() throws CoolsmsException {
//        log.info(userService.sendRandomNumber("010-2287-6873"));
//    }
//
//    @Test
//    public void updateUserPassword() {
//        userService.updateUserPassword("1234", "4321");
//    }
//
//    @Test
//    public void findIdByEmail() {
//        assertThat(userService.findIdByEmail("tonky1234", null, "tonky0810@naver.com")).isNull();
//    }
//
//    @Test
//    public void sendMail() {
//        MailTO mailTO = new MailTO();
//        mailTO.setAddress("tonky0810@naver.com");
//        mailTO.setTitle("새 비밀번호 링크입니다.");
//        mailTO.setMessage("http://localhost:10000/user/changePassword?userIdentification=tonky0810");
//        userService.sendMail(mailTO);
//    }
//
//    @Test
//    public void updateUserRandomKey() {
//        userService.updateUserRandomKey("tonky0810", "123456");
//    }
//
//    @Test
//    public void findByIdentification() {
//        assertThat(userService.findByIdentification(null, "tonky0810@naver.com").getUserId()).isEqualTo(1);
//    }
//
//    @Test
//    public void findByPhone() {
//        assertThat(userService.findIdByPhone(null, null, "010-2287-6873").getUserId() != null);
//    }
//}