package com.Grocery.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class Sendmail {

    @Autowired
    private JavaMailSender mailSender;

    public boolean sendMail(String toEmail, String token) {

        String link = "http://localhost:8080/reset_password?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Grocery Shop <yourEmail@gmail.com>");
        message.setTo(toEmail);
        message.setSubject("Password Reset Request");
        message.setText("Click here to reset your password:\n" + link);

        try {
            mailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}