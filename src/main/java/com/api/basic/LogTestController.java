package com.api.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/tt")
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("log-test")//GetMapping이 우선순위가 높음
    public String logTest2(){
        String name = "Spring";
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);//application.properties 설정 가능
        log.info("info log={}", name);//여기까지가 기본
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        log.debug("debug log2={}", name);//

        return "logTestController End2";
    }

    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId")String data){
//        http://localhost:8080/tt/mapping/wonny
        log.info("mapping userId={}", data);
        return "get mappingPath";
    }

    @GetMapping("/mapping/ommited/{userId2}")
    public String mappingPath2(@PathVariable String userId2){
        //생략 : PathVariable의 변수명과 파라미터 키가 같으면 생략 가능
//        http://localhost:8080/tt/mapping/ommited/wonny
        log.info("mapping userId2={}", userId2);
        return "get mappingPath2";
    }
}
