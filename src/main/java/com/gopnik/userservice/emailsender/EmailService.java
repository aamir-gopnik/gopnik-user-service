package com.gopnik.userservice.emailsender;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
@Slf4j
public class EmailService implements EmailSender{

    private final JavaMailSender mailSender;

    private final static String FROM_EMAIL = "aamirkamaal1994@gmail.com";

    @Async
    public CompletableFuture<Void> send(String to, String subject, String emailContent) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "utf-8");

            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(emailContent, true); // true = HTML content
            messageHelper.setFrom(FROM_EMAIL);

            mailSender.send(mimeMessage);

            log.info("Email sent successfully to: {}", to);
            return CompletableFuture.completedFuture(null);

        } catch (MessagingException e) {
            log.error("Failed to send email to: {}. Error: {}", to, e.getMessage(), e);
            throw new IllegalStateException("Failed to send email", e);
        }
    }

    // Overloaded method for default subject
    @Async
    public CompletableFuture<Void> send(String to, String emailContent) {
        return send(to, "Confirm your email", emailContent);
    }
}
