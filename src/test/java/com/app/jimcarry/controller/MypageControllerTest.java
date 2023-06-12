//package com.app.jimcarry.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@Slf4j
//@Transactional
//class MypageControllerTest {
//
//    @Autowired
//    WebApplicationContext webApplicationContext;
//
//    MockMvc mockMvc;
//
//    @BeforeEach
//    public void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }
//
//    @Test
//    void myBox() throws Exception {
//        log.info(
//                "........getModelAndView" +
//                        mockMvc.perform(MockMvcRequestBuilders.get("/users/mypage/mybox")
//                                .param("page", "1")
//                                .param("amount", "10")
//                        ).andReturn().getModelAndView().getModelMap().toString());
//    }
//
//    @Test
//    void updateUser() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/users/mypage/update")
//                .param("userIdentification", "MypageControllerUpdateTest")
//                .param("userPassword", "4321")
//                .param("userEmail", "ControllerTest@gmail.com")
//                .param("userPhone", "01043214321")
//                .param("userAddress", "수원시 영통구 매탄동")
//                .param("userAddressDetail", "상세주소")
//                .param("userGender", "남")
//                .param("userBirth", "1900-01-01")
//        ).andDo(MockMvcResultHandlers.print())
//                .andExpect(status().is3xxRedirection())
//                .andReturn();
//    }
//
//    @Test
//    void checkIdentificationDuplicate() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/users/mypage/checkIdentification")
//                .param("userIdentification", "ControllerUpdateTest")
//        ).andExpect(MockMvcResultMatchers.content().string("true"));
//        mockMvc.perform(MockMvcRequestBuilders.post("/users/mypage/checkEmail")
//                .param("userIdentification", "agagsdbsrtbhergdfv")
//        ).andExpect(MockMvcResultMatchers.content().string("true"));
//    }
//
//    @Test
//    void checkEmailDuplicate() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/users/mypage/checkEmail")
//                .param("userEmail", "ControllerTest@gmail.com")
//        ).andExpect(MockMvcResultMatchers.content().string("true"));
//        mockMvc.perform(MockMvcRequestBuilders.post("/users/mypage/checkEmail/2")
//                .param("userEmail", "agagsdbsrtbhergdfv")
//        ).andExpect(MockMvcResultMatchers.content().string("true"));
//    }
//
//    @Test
//    void checkPassword() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/users/mypage/checkPassword")
//                .param("userPassword", "4321")
//        ).andExpect(MockMvcResultMatchers.content().string("true"));
//        mockMvc.perform(MockMvcRequestBuilders.post("/users/mypage/checkPassword")
//                .param("userPassword", "sftgsdf23r3qfgsdf")
//        ).andExpect(MockMvcResultMatchers.content().string("false"));
//    }
//
//    @Test
//    void deleteUser() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.delete("/users/mypage/delete"))
//                .andExpect(status().is3xxRedirection()).andExpect(MockMvcResultMatchers.redirectedUrl("/main"));
//    }
//}