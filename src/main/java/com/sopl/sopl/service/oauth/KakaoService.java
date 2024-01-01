package com.sopl.sopl.service.oauth;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sopl.sopl.domain.User;
import com.sopl.sopl.repository.UserRepository;
import com.sopl.sopl.service.RedisService;
import com.sopl.sopl.service.UserService;
import com.sopl.sopl.service.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class KakaoService {

    private final UserService userService;
    private final RedisService redisService;
    private final UserRepository userRepository;

    @Value("${oauth.kakao.client.id}")
    private String kakaoClientId;
    @Value("${oauth.kakao.redirectUrl}")
    private String kakaoRedirectUrl;

    @Transactional
    public String getAccessToken (String authorize_code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // POST 요청을 위해 기본값이 false인 setDoOutput을 true로

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");

            log.info("clientId:: " + kakaoClientId);
            log.info("reUrl:: " + kakaoRedirectUrl);
            sb.append("&client_id=" + kakaoClientId); // REST_API키 본인이 발급받은 key 넣어주기
            sb.append("&redirect_uri=" + kakaoRedirectUrl); // REDIRECT_URI 본인이 설정한 주소 넣어주기

            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            // 결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            // jackson objectmapper 객체 생성
            ObjectMapper objectMapper = new ObjectMapper();
            // JSON String -> Map
            Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {
            });

            access_Token = jsonMap.get("access_token").toString();
            refresh_Token = jsonMap.get("refresh_token").toString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return access_Token;
    }

//    public void login(String accessToken) {
//        redisService.saveToken();
//    }

    public UserInfoDto getUserinfo(
            HttpServletRequest request,
            HttpServletResponse response,
            String access_token) {
//        HashMap<String, Object> userInfo = new HashMap<String, Object>();

        String requestURL = "https://kapi.kakao.com/v2/user/me";

        try {
            URL url = new URL(requestURL); //1.url 객체만들기
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //2.url 에서 url connection 만들기
            conn.setRequestMethod("GET"); // 3.URL 연결구성
            conn.setRequestProperty("Authorization", "Bearer " + access_token);

            //키값, 속성 적용
            int responseCode = conn.getResponseCode(); //서버에서 보낸 http 상태코드 반환
            System.out.println("responseCode :" + responseCode + "여긴가");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            // 버퍼를 사용하여 일근ㄴ것
            String line = "";
            String result = "";
            while ((line = buffer.readLine()) != null) {
                result += line;
            }
            //readLine()) ==> 입력 String 값으로 리턴값 고정

            System.out.println("response body :" + result);

            JSONObject jsonObject = new JSONObject(result);
            Long id = jsonObject.getLong("id");
            String email = jsonObject.getJSONObject("kakao_account").getString("email");
            String nickname = jsonObject.getJSONObject("properties").getString("nickname");

//            userInfo.put("id", id);
//            userInfo.put("email", email);
//            userInfo.put("nickname", nickname);

            User user = User.builder().id(id).email(email).nickname(nickname).build();
            UserInfoDto userInfo = new UserInfoDto(id, email);

            /* 유저 테이블에 존재하는지 확인 */
            Optional<User> userData = userRepository.findUserById(userInfo.getUserId());

            log.info("111" + userData.isPresent());

            /* 유저 테이블에 있으면 로그인 처리, 없으면 넣고 로그인 처리 */
            if (userData.isPresent()) {
                // 로그인
                userService.loginUser(request, response, userInfo, access_token);
            } else {
                // 회원가입
                userRepository.save(user);
                // 로그인
                userService.loginUser(request, response, userInfo, access_token);
            }

            return userInfo;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
