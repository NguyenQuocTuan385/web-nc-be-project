package com.group6.ads.services.email;

import com.group6.ads.controllers.email.models.OTPRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    String emailFrom;

    @Autowired
    private TemplateEngine templateEngine;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void sendOTPToEmail(OTPRequest otpRequest, String subject, String templateName, Context context) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            logger.info("Send OTP to email: {}", otpRequest.getEmail());
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(otpRequest.getEmail());
            helper.setSubject(subject);

            String htmlContent = templateEngine.process(templateName, context);
            helper.setText(htmlContent, true);  // Set true để chỉ định rằng nội dung là HTML

            mailSender.send(message);

        } catch (MessagingException e) {
            // Xử lý ngoại lệ nếu có lỗi khi gửi email
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }


    public void sendTextEmail(String toEmail,
                          String subject,
                          String body) {
        try {
            logger.info("Send text email to email: {}", toEmail);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailFrom);
            message.setTo(toEmail);
            message.setText(body);
            message.setSubject(subject);

            mailSender.send(message);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendHtmlEmail(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(htmlBody, true);

        mailSender.send(mimeMessage);
        logger.info("Send html email to email: {}", to);
    }
}
