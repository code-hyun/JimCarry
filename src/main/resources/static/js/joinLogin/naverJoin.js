var naver_id_login = new naver_id_login("EVE2RFzPw29yZMdwRAx9", "http://52.79.90.95:10000/main/");
// 접근 토큰 값 출력
naver_id_login.oauthParams.access_token;
// 네이버 사용자 프로필 조회
naver_id_login.get_naver_userprofile("naverSignInCallback()");
// 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
function naverSignInCallback() {
    $('input[name=userName]').val(naver_id_login.getProfileData('name'));
    $('input[name=userName]').attr('readonly', true);
    $('input[name=userEmail]').val(naver_id_login.getProfileData('email'));
    $('input[name=userEmail]').attr('readonly', true);

    $('input[name=userStatus]').val(2);
    // alert(naver_id_login.getProfileData('name'));
    // alert(naver_id_login.getProfileData('email'));
    // alert(naver_id_login.getProfileData('gender'));
}