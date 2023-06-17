package com.app.jimcarry.service;

import com.app.jimcarry.aspect.annotation.Encryption;
import com.app.jimcarry.aspect.annotation.LogStatus;
import com.app.jimcarry.domain.dao.UserDAO;
import com.app.jimcarry.domain.dto.PageDTO;
import com.app.jimcarry.domain.dto.SearchDTO;
import com.app.jimcarry.domain.vo.MailTO;
import com.app.jimcarry.domain.vo.UserVO;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
/*추후에 클래스명 변경 요망*/
public class UserService {
    private final UserDAO userDAO;

    /**
     * 회원가입 서비스
     *
     * @param userVO 화면에서 입력받은 회원정보
     * @throws NoSuchElementException   회원정보VO NULL값 들어옴
     * @throws IllegalArgumentException 중복된 회원 아이디가 들어옴
     */
    @LogStatus
    @Encryption /* 회원 비밀번호 암호화 Aspect */
    @Transactional(rollbackFor = Exception.class)
    public void registerUser(UserVO userVO) {
//        userVO null 검사
        Optional.ofNullable(userVO).orElseThrow();
        String userIdentification = userVO.getUserIdentification();

        if(userVO.getUserGender() == null) {
            userVO.setUserGender("선택 안함");
        }

        userVO.setUserBirth(userVO.getUserBirth().replace(",", "-"));
        String[] userBirth = userVO.getUserBirth().split("-");

        /* 아이디 중복검사 */
        if (checkIdentificationDuplicate(userIdentification)) {
            userDAO.save(userVO);
        } else throw new IllegalArgumentException("Duplicate Identification");
    }

    /**
     * 회원조회 서비스
     *
     * @param userId 화면에서 입력받은 회원번호
     * @throws NoSuchElementException 조회된 회원 없음
     */
    @LogStatus
    public UserVO getUser(Long userId) {
        return Optional.ofNullable(userDAO.findById(userId)).get();
    }

    /**
     * 검색조건을 통한 회원조회 서비스
     *
     *  @param pageDTO 화면에서 받아온 페이징처리 정보, Criteria, SearchDTO 포함
     */
    @LogStatus
    public List<UserVO> getUserListBy(PageDTO pageDTO) {
        return userDAO.findListBy(pageDTO);
    }

    /**
     * 전체회원 조회 서비스
     *
     * @param pageDTO 화면에서 받은 페이징 정보
     */
    @LogStatus
    public List<UserVO> getList(PageDTO pageDTO) {
        return userDAO.findAll(pageDTO);
    }

    /**
     * 회원정보 업데이트 서비스
     *
     * @param userVO 화면에서 입력받은 회원정보
     */
    @Encryption
    @LogStatus
    @Transactional
    public void updateUser(UserVO userVO) {
        userDAO.setUserVO(userVO);
    }
    public void modify(UserVO userVO) {
        userDAO.setAdminUserVO(userVO);
    }

    /**
     * 회원정보 삭제 서비스
     *
     * @param userId 회원번호
     * @throws NoSuchElementException 해당 회원번호가 존재하지 않음
     */
    @LogStatus
    public void removeUser(Long userId) {
        Optional.ofNullable(userDAO.findById(userId)).orElseThrow();
        userDAO.deleteById(userId);
    }

    /**
     * 로그인 서비스
     */
    @LogStatus
    @Encryption
    public UserVO login(String userIdentification, String userPassword) {
        return userDAO.login(userIdentification, userPassword);
    }


    /**
     * 회원 아이디 중복검사 메소드
     * 사용가능 = true, 사용불가 = false
     *
     * @param userIdentification 회원 아이디
     */
    @LogStatus
    public boolean checkIdentificationDuplicate(String userIdentification) {
        //     사용가능 = true, 사용불가 = false
        return userDAO.findCountByUserIdentification(userIdentification) == 0;
    }

    /**
     * 회원 이메일 중복검사 메소드
     * 사용가능 = true, 사용불가 = false
     *
     * @param userEmail 회원 이메일
     */
    @LogStatus
    public boolean checkEmailDuplicate(String userEmail) {
        //     사용가능 = true, 사용불가 = false
        log.info("checkEmailDuplicate : " + (userDAO.findCountByUserEmail(userEmail) == 0));
        return userDAO.findCountByUserEmail(userEmail) == 0;
    }

//    랜덤 숫자 키 뽑기
    public String randomKey() {
        Random random = new Random();
        String randomNum = "";

        for(int i = 0; i < 6; i++) {
            String number = Integer.toString(random.nextInt(10));
            randomNum += number;
        }

        return randomNum;
    }

//      랜덤 숫자 뽑아서 문자 메세지 전송
    public String sendRandomNumber(String userPhone) throws CoolsmsException {
        String api_key = "NCSW9JM1RREOSKPR";
        String api_secret = "CWPWKNOLA3D0FD94JOY4W6Q2SBYXVSOK";
        Message coolsms = new Message(api_key, api_secret);

        String numStr = randomKey();

//        회원가입에서 -가 붙어서 오기 때문에 떼어주는것
        userPhone = userPhone.replace("-", "");

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", userPhone);    // 수신전화번호 (ajax로 view 화면에서 받아온 값으로 넘김)
        params.put("from", "01022876873");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "sms");
        params.put("text", "인증번호는 [" + numStr + "] 입니다.");

        coolsms.send(params); // 메시지 전송

        return numStr;
    }

//    이름, 전화번호로 아이디 찾기
    public UserVO findIdByPhone(String userIdentification, String userName, String userPhone) {
        return userDAO.findByPhoneAndNameOrIdentification(userIdentification, userName, userPhone);
    }

    //    이름, 이메일로 찾기
    public UserVO findIdByEmail(String userIdentification, String userName, String userEmail) {
        return userDAO.findByEmailAndNameOrIdentification(userIdentification, userName, userEmail);
    }

//    비밀번호 변경
    @LogStatus
    @Encryption /* 회원 비밀번호 암호화 Aspect */
    @Transactional(rollbackFor = Exception.class)
    public void updateUserPassword(String userIdentification, String userPassword) {
        userDAO.setPasswordByIdentification(userIdentification, userPassword);
    }


    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(MailTO mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getAddress());
        message.setFrom("disappointed123419@gmail.com");
//        from 값을 설정하지 않으면 application.yml의 username값이 설정됩니다.
        message.setSubject(mail.getTitle());
        message.setText(mail.getMessage());

        mailSender.send(message);
    }
    public int getTotalUser(){
        return userDAO.findTotal();
    }
    // 회원 리스트 조건 조회
    public int findTotalBy(SearchDTO searchDTO){ return userDAO.findTotalBy(searchDTO);}

    //    비밀번호 변경 이메일 발송시 랜덤 키 값 컬럼에 저장
    //    비밀번호 변경 완료 시 랜덤 키 컬럼 값 삭제
    public void updateUserRandomKey(String userIdentification, String userRandomKey) {
        userDAO.setUserRandomKeyByIdentification(userIdentification, userRandomKey);
    }

    //    아이디로 랜덤키 찾기
    public UserVO findByIdentification(String userIdentification, String userEmail) {
        return userDAO.findByIdentificationUser(userIdentification, userEmail);
    }



    public String getKaKaoAccessToken(String code, String type){
        String access_Token="";
        String refresh_Token ="";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try{
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=f98c5a800722daf40b2e538f2766c211"); // TODO REST_API_KEY 입력

//            회원가입에서 접근했을 때
            if(type.equals("join")) {
                sb.append("&redirect_uri=http://www.jimcarry.site/user/kakao"); // TODO 인가코드 받은 redirect_uri 입력
            } else if (type.equals("login")) {
//            로그인에서 접근했을 때
                sb.append("&redirect_uri=http://www.jimcarry.site/user/kakao-login"); // TODO 인가코드 받은 redirect_uri 입력
            }
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            log.info("responseCode : " + responseCode);
            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            log.info("response body : " + result);

            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            log.info("access_token : " + access_Token);
            log.info("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }


    public UserVO getKakaoInfo(String token) throws Exception {

        UserVO kakaoInfo = new UserVO();
        String reqURL = "https://kapi.kakao.com/v2/user/me";

        //access_token을 이용하여 사용자 정보 조회
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            log.info("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            log.info("response body : " + result);

            //Gson 라이브러리로 JSON파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            int id = element.getAsJsonObject().get("id").getAsInt();
            boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
            String userEmail = "";
            if(hasEmail){
                userEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
            }

            log.info("id : " + id);
            log.info("email : " + userEmail);
            kakaoInfo.setUserEmail(userEmail);

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return kakaoInfo;
    }


    public void logoutKakao(String token){
        String reqURL ="https://kapi.kakao.com/v1/user/logout";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Authorization", "Bearer " + token);
            int responseCode = conn.getResponseCode();
            log.info("responseCode : " + responseCode);

            if(responseCode ==400)
                throw new RuntimeException("카카오 로그아웃 도중 오류 발생");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String br_line = "";
            String result = "";
            while ((br_line = br.readLine()) != null) {
                result += br_line;
            }
            log.info("결과");
            log.info(result);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}
