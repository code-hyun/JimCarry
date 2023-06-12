//package com.app.jimcarry.domain.dao;
//
//import com.app.jimcarry.domain.dto.PageDTO;
//import com.app.jimcarry.domain.dto.SearchDTO;
//import com.app.jimcarry.domain.vo.Criteria;
//import com.app.jimcarry.domain.vo.UserVO;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.*;
//
//import static org.assertj.core.api.Assertions.*;
//
//@SpringBootTest
//@Slf4j
//@Transactional
//class UserDAOTest {
//    @Autowired
//    UserDAO userDAO;
//    @Autowired
//    UserVO userVO;
//
//    @BeforeEach
//    void beforeEachSetUserVO() {
//        userVO.setUserIdentification("userMapperTest");
//        userVO.setUserPassword("1234");
//        userVO.setUserEmail("userMapperTest@gmail.com");
//        userVO.setUserPhone("01012341234");
//        userVO.setUserAddress("서울시 강남구 역삼동");
//        userVO.setUserAddressDetail("경리단길 123");
//        userVO.setUserName("테스트");
//        /*userVO.setUserGender(null);*/ // 선택안함
////        userVO.setUserGender("선택 안함"); // 선택안함
//        userVO.setUserBirth("1900-01-01");
//    }
//
//    @Test
//    void save() {
//        userDAO.save(userVO);
//    }
//
//    @Test
//    void findById() {
//        userDAO.save(userVO);
//        userDAO.findById(userVO.getUserId());
//    }
//
//    @Test
//    void findListBy() {
//        int total = userDAO.findTotal();
//        Criteria criteria = new Criteria().create(1, 10);
//        SearchDTO searchDTO = new SearchDTO();
//        searchDTO.setTypes(new ArrayList<>(Arrays.asList("keyword")));
//        searchDTO.setKeyword("Keyword");
//        PageDTO pageDTO = new PageDTO().createPageDTO(criteria, total, searchDTO);
//        userDAO.findListBy(pageDTO);
//    }
//
//    @Test
//    void findTotalBy() {
//        SearchDTO searchDTO = new SearchDTO();
//        searchDTO.setTypes(new ArrayList<>(Arrays.asList("keyword")));
//        searchDTO.setKeyword("Keyword");
//        userDAO.findTotalBy(searchDTO);
//    }
//
//    @Test
//    void findAll() {
//        int total = userDAO.findTotal();
//        Criteria criteria = new Criteria().create(1, 10);
//        PageDTO pageDTO = new PageDTO().createPageDTO(criteria, total, new SearchDTO());
//        List<UserVO> users = userDAO.findAll(pageDTO);
//
//        if (users.size() == 0) {
//            assertThat(users.size()).isEqualTo(0);
//        } else {
//            users.forEach(user -> assertThat(user).isInstanceOf(UserVO.class));
//        }
//    }
//
//    @Test
//    void setUserVO() {
//        String test = "testUpdate";
//        Optional.ofNullable(userDAO.findById(1L)).ifPresent(user -> {
//            String id = user.getUserIdentification();
//            user.setUserIdentification(test);
//            userDAO.setUserVO(user);
//
//            assertThat(Optional.ofNullable(userDAO.findById(user.getUserId())).get().getUserIdentification()).isNotEqualTo(id);
//        });
//    }
//
//    @Test
//    void deleteById() {
//        userDAO.save(userVO);
//        userDAO.deleteById(userVO.getUserId());
//        Assertions.assertThrows(NoSuchElementException.class, () ->
//                Optional.ofNullable(userDAO.findById(userVO.getUserId())).get()
//        );
//    }
//
//    @Test
//    void login() {
//        assertThat(userDAO.login("test", "NDMyMQ==")).isNotNull();
//    }
//
//    @Test
//    void findCountByUserIdentification() {
//        userVO.setUserIdentification("findCountByUserIdentification");
//        userDAO.save(userVO);
//        assertThat(userDAO.findCountByUserIdentification("findCountByUserIdentification")).isGreaterThan(0);
//    }
//
//    @Test
//    void findCountByUserEmail() {
//        userDAO.save(userVO);
//        assertThat(userDAO.findCountByUserEmail(userVO.getUserEmail())).isGreaterThan(0);
//    }
//
//    @Test
//    public void setPasswordByIdentification() {
//        userDAO.setPasswordByIdentification("1234", "4321");
//    }
//
//    @Test
//    public void updateRandomKeyByUserIdentification() {
//        userDAO.setUserRandomKeyByIdentification("tonky0810", "123456");
//    }
//}