package com.example.studyspringmvc2.web;


import com.example.studyspringmvc2.service.WxgzhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SampleController {

    @Autowired
    WxgzhService userService;

    @RequestMapping(value={"/hello"})
    public String home() {
        System.out.println("api hello");
        return "Hello World!";
    }




}
