package com.api.receive;

import com.api.domain.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    //클라이언트 -> 서버
    //파라미터로 데이터 보냄
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String userName = req.getParameter("userName");
        int age = Integer.parseInt(req.getParameter("age"));

        log.info("userName={}, age={}", userName, age);

        StringBuilder sb = new StringBuilder();
        sb.append("userName>> "+ userName);
        sb.append(System.getProperty("line.separator"));
        sb.append("age>> "+age);
        res.getWriter().write(sb.toString());

    }

    // @RequestMapping
    // class 단위 매핑

    // @Responsebody
    // 서버 --> 클라이언트
    // 자바 객체 --> body로 전달

    // @RequestBody
    // 클라이언트 --> 서버
    // body 내용(xml, json)--> 자바 객체로 전달

    // @RequestParam
    // 클라이언트 -> 서버
    //파라미터 키-값 보냄

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {
        log.info("username={}, age={}", memberName, memberAge);
        StringBuilder sb = new StringBuilder();
        sb.append("userName>> "+ memberName);
        sb.append(System.getProperty("line.separator"));
        sb.append("age>> "+memberAge);

        return sb.toString();
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {

        StringBuilder sb = new StringBuilder();
        sb.append("userName>> "+ username);
        sb.append(System.getProperty("line.separator"));
        sb.append("age>> "+age);

        return sb.toString();
    }

    // @RequestParam 생략
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);

        StringBuilder sb = new StringBuilder();
        sb.append("userName>> "+ username);
        sb.append(System.getProperty("line.separator"));
        sb.append("age>> "+age);

        return sb.toString();
    }

    // int -> null 저장 불가능
    // Integer -> null 값 저장 가능

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {

        log.info("username={}, age={}", username, age);

        StringBuilder sb = new StringBuilder();
        sb.append("userName>> "+ username);
        sb.append(System.getProperty("line.separator"));
        sb.append("age>> "+age);

        return sb.toString();
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {

        log.info("username={}, age={}", username, age);

        StringBuilder sb = new StringBuilder();
        sb.append("userName>> "+ username);
        sb.append(System.getProperty("line.separator"));
        sb.append("age>> "+age);

        return sb.toString();
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        StringBuilder sb = new StringBuilder();
        sb.append("userName>> "+ paramMap.get("username"));
        sb.append(System.getProperty("line.separator"));
        sb.append("age>> "+paramMap.get("age"));

        return sb.toString();
    }

    // @RequestParam -> 단순 타입 : String, int, Integer

    // @ModelAttribute: 파라미터 키-값 -> 객체
    //helloData 이름으로 자바 객체의 프로퍼티 찾는다
    // 객체에 속한 파리미터 값을 바인딩한다

    //localhost:8080/model-attribute-v1?username=wonny&age=12

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";//text/plain;charset=UTF-8
    }

    // @ModelAttribute 생략 가능
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public HelloData modelAttributeV2(HelloData helloData) {
        return helloData;//application/json
    }
}
