package com.api.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@Slf4j
public class ApiTestController {

    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable String orderId){
//        http://localhost:8080/api/mapping/users/wonny/orders/1200
        log.info("mappig Path userId={}, orderId={}", userId, orderId);
        return "mapping Path";
    }

    //특정 파라미터 조건 매핑
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam(){
//        http://localhost:8080/api/mapping-param?mode=debug
        log.info("mapping Param");
        return "mapping Param ok";
    }

    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader(){
        log.info("mapping header");
        return "mapping header ok";
    }

    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes(){
//        localhost:8080/api/mapping-consume
//        Content-Type application/json -> client가 서버에게 json으로 보낸다
        log.info("mapping Consumes");
        return "mapping Consumes ok";
    }

    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces(){
//        localhost:8080/api/mapping-produce
//        produces : 생산 가능한 미디어 타입 -> 클리이언트가 서버한테 html로 돌려줘라고 요청한다
        log.info("mapping Produces");
        return "mapping Produces ok";
    }

}
