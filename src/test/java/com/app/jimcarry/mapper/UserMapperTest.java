//package com.app.jimcarry.mapper;
//
//import com.app.jimcarry.domain.dto.PageDTO;
//import com.app.jimcarry.domain.dto.SearchDTO;
//import com.app.jimcarry.domain.vo.Criteria;
//import com.app.jimcarry.domain.vo.UserVO;
//import lombok.extern.slf4j.Slf4j;
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
////@Transactional
//class UserMapperTest {
//
//    @Autowired
//    UserMapper userMapper;
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
//        /*userVO.setUserGender(null);*/ // 선택안함
////        userVO.setUserGender("선택 안함"); // 선택안함
//        userVO.setUserBirth("1900-01-01");
//
//
//    }
//
//    @Test
//    void insert() {
//        /* insert 시 userId 받아오는가 테스트 */
//        userMapper.insert(userVO);
//        assertThat(userVO.getUserId()).isNotNull();
//        log.info(userVO.getUserId().toString());
//
//        /* insert 정상작동 테스트 */
//        assertDoesNotThrow(() -> userMapper.insert(userVO));
//        assertThrows(Exception.class, () -> userMapper.insert(null));
//    }
//
//    @Test
//    void select() {
//        userMapper.insert(userVO);
//        assertThat(userMapper.select(userVO.getUserId()).getUserId())
//                .isEqualTo(userVO.getUserId());
//    }
//
//    @Test
//    void selectAllBy() {
//        int total = 0;
//
//        SearchDTO searchDTO = new SearchDTO();
//        Criteria criteria = new Criteria().create(1, 10);
//        searchDTO.setTypes(new ArrayList<>(Arrays.asList("keyword")));
//        searchDTO.setKeyword("Keyword");
//        total = userMapper.totalBy(searchDTO);
//
//        userMapper.selectAllBy(new PageDTO().createPageDTO(criteria, total, searchDTO));
//    }
//
//    @Test
//    void selectAll() {
//        Criteria criteria = new Criteria().create(1, 10);
//        SearchDTO searchDTO = new SearchDTO();
//        searchDTO.setDesc(true);
//        userMapper.selectAll(new PageDTO().createPageDTO(criteria, userMapper.total(), searchDTO));
//    }
//
//    @Test
//    void totalBy() {
//        SearchDTO searchDTO = new SearchDTO();
//        searchDTO.setTypes(new ArrayList<>(Arrays.asList("keyword")));
//        searchDTO.setKeyword("Keyword");
//        userMapper.totalBy(searchDTO);
//    }
//
//    @Test
//    void total() {
//        userMapper.total();
//    }
//
//    @Test
//    void update() {
//        String update = "updateduserMapperTest";
//        userMapper.insert(userVO);
//        userVO.setUserIdentification(update);
//        userMapper.update(userVO);
//        assertThat(userMapper.select(userVO.getUserId()).getUserIdentification()).isEqualTo(update);
//    }
//
//    @Test
//    void delete() {
//        userMapper.insert(userVO);
//        userMapper.delete(userVO.getUserId());
//        assertThat(userMapper.select(userVO.getUserId())).isNull();
//    }
//
//    @Test
//    void selectByIdentificationAndPassword() {
//        userMapper.insert(userVO);
//        assertThat(userMapper.selectByIdentificationAndPassword("userMapperTest", "1234")).isEqualTo(userVO.getUserId());
////        assertThat(userMapper.selectByIdentificationAndPassword("userServiceDuplicateTest", "MTIzNA==")).isNotNull();
//    }
//
//    @Test
//    void selectBytIdentification() {
//        userVO.setUserIdentification("randomTestUnit");
//        userMapper.insert(userVO);
//        assertThat(userMapper.selectByIdentification(userVO.getUserIdentification())).isEqualTo(1);
//    }
//
//    @Test
//    void selectByEmail() {
//        userMapper.insert(userVO);
//        assertThat(userMapper.selectByEmail(userVO.getUserEmail())).isGreaterThan(0);
//    }
//
//    @Test
//    public void selectByNameAndPhone() {
//        userMapper.selectByPhoneAndNameOrIdentification("tonky0810", null, "010-2287-6873");
//    }
//
//    @Test
//    public void selectByEmailAndNameOrIdentification() {
//        assertThat(userMapper.selectByEmailAndNameOrIdentification(null, "정현진", "tonky0810@naver.com")
//        .getUserId()).isEqualTo(1);
//    }
//
//    @Test
//    public void updatePasswordByIdentification() {
//        userMapper.updatePasswordByIdentification("1234", "1234");
//    }
//
//    @Test
//    public void updateRandomKeyByUserIdentification() {
//        userMapper.updateRandomKeyByUserIdentification("tonky0810", "123456");
//    }
//
//    @Test
//    public void selectByIdentificationUser() {
//        assertThat(userMapper.selectByIdentificationUser("tonky0810", null).getUserIdentification()).isEqualTo("tonky0810");
//    }
//}