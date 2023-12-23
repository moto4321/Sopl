package com.sopl.sopl.service.oauth;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@Slf4j
@Service
public class KakaoService {

    @Value("${oauth.kakao.client.id}")
    private String kakaoClientId;
    @Value("${oauth.kakao.redirectUrl}")
    private String kakaoRedirectUrl;

    public String getAccessToken (String authorize_code) {
//        String access_Token = "";
//        String refresh_Token = "";
//        String reqURL = "https://kauth.kakao.com/oauth/token";
//
//        try {
//            URL url = new URL(reqURL);
//
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//            //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로
//
//            conn.setRequestMethod("POST");
//            conn.setDoOutput(true);
//
//            //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//            StringBuilder sb = new StringBuilder();
//            sb.append("grant_type=authorization_code");
//            sb.append("&client_id=" + kakaoClientId);  //본인이 발급받은 key
//            sb.append("&redirect_uri=" + kakaoRedirectUrl);     // 본인이 설정해 놓은 경로
//            sb.append("&code=" + authorize_code);
//            bw.write(sb.toString());
//            bw.flush();
//
//
//            //    결과 코드가 200이라면 성공
//            int responseCode = conn.getResponseCode();
//            System.out.println("responseCode : " + responseCode);
//
//            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
//            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String line = "";
//            String result = "";
//
//            while ((line = br.readLine()) != null) {
//                result += line;
//            }
//            System.out.println("response body : " + result);
//
//
//
//            //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
////            JSONParser parser = new JSONParser();
////            JsonElement element = parser.parse(result);
////
////            access_Token = element.getAsJsonObject().get("access_token").getAsString();
////            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
////
////            System.out.println("access_token : " + access_Token);
////            System.out.println("refresh_token : " + refresh_Token);
////
////            br.close();
////            bw.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return access_Token;

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
}
