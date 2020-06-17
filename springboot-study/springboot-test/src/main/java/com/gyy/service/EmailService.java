package com.gyy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Async  //表示是异步任务
    public void sendEmail(String to,String from,String text){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setSubject("喜讯");
        simpleMailMessage.setText(text);
        mailSender.send(simpleMailMessage);
        System.out.println("发送成功了");
    }
}
