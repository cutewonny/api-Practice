package com.api.receive;

import com.api.domain.HelloData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectOutput;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {
    private ObjectMapper objectMapper = new ObjectMapper();

    // 클라이언트: JSON Body: {"username":"jsonWonny", "age": 33}
    // --> 서버: 자바 객체: HelloData(username=jsonWonny, age=33)

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest req, HttpServletResponse res) throws IOException {
        ServletInputStream inputStream = req.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        HelloData data = objectMapper.readValue(messageBody, HelloData.class);

        res.getWriter().write(data.toString());

    }


    // @RequestBody
    // 클라이언트 --> 서버
    // body (xml, json) 요청--- Http 메세지 컨버터 ---> 자바 객체로 전달

    // @Responsebody
    // 서버 --> 클라이언트
    // 자바 객체 --- Http 메세지 컨버터 ---> body로 응답

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws JsonProcessingException {
        HelloData data = objectMapper.readValue(messageBody, HelloData.class);
        return data.toString();
    }

    // objectMapper 안 쓰기 -> @RequestBody 객체
    // @RequestBody 생략 불가능
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public HelloData requestBodyJsonV3(@RequestBody HelloData helloData){
        return helloData;
    }

    //HttpEntity 사용
    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public HelloData requestBodyJsonV4(HttpEntity<HelloData> httpEntity){
        HelloData data = httpEntity.getBody();
        return data;
    }

}
