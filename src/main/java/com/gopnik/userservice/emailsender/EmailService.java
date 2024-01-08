package com.gopnik.userservice.emailsender;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class EmailService implements EmailSender{

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String email){
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,"utf-8");
            messageHelper.setText(email,true);
            messageHelper.setTo(to);
            messageHelper.setSubject("Confirm your email");
            messageHelper.setFrom("userservice@gopnik.com");
        } catch (MessagingException me){
            LOGGER.error("Failed to send email ", me);
            throw new IllegalStateException("Failed to send email");
        }
    }
}