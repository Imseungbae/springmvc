package com.springmvc.basic.request;

import com.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge){
        log.debug("v2) membername ={}, memberAge={} ", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam(required = false, defaultValue = "hi") String username,
            @RequestParam(required = true) int age){
        log.debug("v3) username ={}, age={} ", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){
        log.debug("v4) username ={}, age={} ", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam Map<String, Object> paramMap){

        log.debug("username:{}) ", paramMap.get("username"));
        log.debug("age:{}) ", paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String requestParamMap(@ModelAttribute HelloData helloData){

        log.debug("username:{}, age:{}:::::::::::::", helloData.username, helloData.age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String requestParamMapV2(HelloData helloData){

        log.debug("v2] username:{}, age:{}:::::::::::::", helloData.username, helloData.age);
        return "ok";
    }
}
