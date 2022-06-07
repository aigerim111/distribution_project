package com.example.demo.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSender {

    private JavaMailSender mailSender;

    String subject = "Verification";
    String message = "To activate your account press the link"
            + "\n\nhttp://localhost:8083/activate/\n\n"
            + "That link will expire after in 2 hours";

    public void sendMail(String emailTo, /*String org,*/ String activationCode){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("umarovdias2002@gmail.com");
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message + activationCode);

        mailSender.send(mailMessage);
    }

}
