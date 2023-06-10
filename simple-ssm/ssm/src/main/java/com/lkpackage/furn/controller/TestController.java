package com.lkpackage.furn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author UnknownCode
 * @version 1.0
 * @date 2023/6/8 19:13
 */
@Controller
public class TestController {

    @RequestMapping("/hi")
    public String h1(){
        System.out.println("进入hi 方法");
        return "hi";
    }
}
