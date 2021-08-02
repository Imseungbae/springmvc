package com.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springmvc.basic.HelloData;
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
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.debug("messageBody={}", messageBody);
        HelloData data = objectMapper.readValue(messageBody, HelloData.class);
        log.debug("username={}", data.username);
        log.debug("age={}", data.age);
        response.getWriter().write("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {

        log.debug("messageBody={} :::::::::::::::::::::::", messageBody);
        HelloData data = objectMapper.readValue(messageBody, HelloData.class);
        log.debug("username={}", data.username);
        log.debug("age={}", data.age);
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {

        log.debug("helloData={} :::::::::::::::::::::::", helloData);
        log.debug("username={}", helloData.username);
        log.debug("age={}", helloData.age);
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) throws IOException {

        HelloData helloData = httpEntity.getBody();
        log.debug("username={}", helloData.username);
        log.debug("age={}", helloData.age);
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) throws IOException {

        log.debug("helloData={} :::::::::::::::::::::::", helloData);
        log.debug("username={}", helloData.username);
        log.debug("age={}", helloData.age);
        return helloData;
    }
}
