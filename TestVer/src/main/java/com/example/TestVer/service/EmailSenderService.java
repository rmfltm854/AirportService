package com.example.TestVer.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;
    public void sendEmail(String toEmail,
                          String subject,
                          String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jms6631@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);

        System.out.println("Mail Sent Success");
    }
    public int[] makeRandomNumber(){
        int[] numAns = new int[5];
        for(int i =0;i<5;i++) {
            numAns[i] = (int) ((Math.random() * 10000) % 10);
        }
        return numAns;
    }
}
