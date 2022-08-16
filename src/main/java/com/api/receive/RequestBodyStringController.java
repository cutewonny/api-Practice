package com.api.receive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    /* body를 읽는 법
    1) HttpServletRequest에서 getInputStream()
    2) HttpEntity 에서 getBody()
     */

    // 클라이언트 Body --> 서버 Body 응답
    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest req, HttpServletResponse res) throws IOException {
        ServletInputStream inputStream = req.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        res.getWriter().write(messageBody);

    }

    // inputStream으로 바로 받기 -> Writer 리턴
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer resWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        resWriter.write(messageBody);

    }

    /*
    HttpEntity : HTTP header, body 정보를 편하게 조회
    응답에도 사용 가능
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity){
        String messageBody = httpEntity.getBody();
        return new HttpEntity<>(messageBody);
        //text/plain;charset=UTF-8
    }

    @PostMapping("/request-body-string-v4")
    public HttpEntity<String> requestBodyStringV4(HttpEntity<String> httpEntity){
        String messageBody = httpEntity.getBody();

        HttpHeaders headers= new HttpHeaders();
        headers.set("MyResponseHeader", "MyValue");
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//        return new HttpEntity<>(messageBody);
        return new ResponseEntity<String>(messageBody, headers, HttpStatus.CREATED);
        //application/json;charset=UTF-8
    }

    // @RequestBody
    // 클라이언트 --> 서버
    // body (xml, json) 요청--- Http 메세지 컨버터 ---> 자바 객체로 전달

    // @Responsebody
    // 서버 --> 클라이언트
    // 자바 객체 --- Http 메세지 컨버터 ---> body로 응답

    @ResponseBody
    @PostMapping("/request-body-string-v5")
    public String requestBodyStringV4(@RequestBody String messageBody){
        return messageBody;
        //text/plain;charset=UTF-8
    }
}
