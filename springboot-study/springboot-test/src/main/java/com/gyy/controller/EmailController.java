package com.gyy.controller;

import com.gyy.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/sayHello")
    @Scheduled(cron = "0 0 4 * * 0-7")
    public String sendHello(){
        emailService.sendEmail("wlan10@sina.com","1592221415@qq.com","sorry!");
        return "ok";
    }
}
