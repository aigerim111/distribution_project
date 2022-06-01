package com.example.demo.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSender {

    private JavaMailSender mailSender;

    String subject = "Verification";
    String message = "To activate your account press the link" + "\nhttp://localhost:8083/activate/";

    public void sendMail(String emailTo, String org, String activationCode){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(org);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message + activationCode);

        mailSender.send(mailMessage);
    }

}
