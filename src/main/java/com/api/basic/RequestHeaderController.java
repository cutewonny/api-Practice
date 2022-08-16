package com.api.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest req,
                          HttpServletResponse res,
                          HttpMethod httpMethod,
                          Locale locale,
                          @RequestHeader MultiValueMap<String, String> headerMap,
                          @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie", required = false) String cookie
                          ){
        StringBuffer sb = new StringBuffer();
        sb.append("req>> "+req);
        sb.append(System.getProperty("line.separator"));

        sb.append("res>>> "+res);
        sb.append(System.getProperty("line.separator"));

        sb.append("httpMethod>> "+httpMethod);
        sb.append(System.getProperty("line.separator"));

        sb.append("locale>>> "+locale);
        sb.append(System.getProperty("line.separator"));

        sb.append("headerMap>>> "+headerMap);
        sb.append(System.getProperty("line.separator"));

        sb.append("host>> "+host);
        sb.append(System.getProperty("line.separator"));

        sb.append("cookie>>>> "+cookie);
        sb.append(System.getProperty("line.separator"));

        return sb.toString();
    }
}
