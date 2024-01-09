package com.group6.ads.controllers.email;

import com.group6.ads.controllers.email.models.EmailSenderRequest;
import com.group6.ads.controllers.email.models.OTPRequest;
import com.group6.ads.exceptions.NotFoundException;
import com.group6.ads.repositories.database.users.User;
import com.group6.ads.repositories.database.users.UserRepository;
import com.group6.ads.services.email.EmailSenderService;
import com.group6.ads.util.OtpUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/email/")
@CrossOrigin("*")
public class EmailSenderController {
    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpUtil otpUtil;
    @PostMapping("text")
    public ResponseEntity<String> sendTextEmail(@RequestBody EmailSenderRequest emailSenderRequest) {
        String to = emailSenderRequest.getToEmail();
        String subject = emailSenderRequest.getSubject();
        String body = emailSenderRequest.getBody();

        emailSenderService.sendTextEmail(to, subject, body);

        return ResponseEntity.ok().body("Sent email successfully!");
    }

    @PostMapping("/html")
    public ResponseEntity<String> sendHtmlEmail(@RequestBody EmailSenderRequest emailSenderRequest) {
        String to = emailSenderRequest.getToEmail();
        String subject = emailSenderRequest.getSubject();
        String htmlBody = emailSenderRequest.getBody();

        try {
            emailSenderService.sendHtmlEmail(to, subject, htmlBody);
            return ResponseEntity.ok().body("Sent email successfully!");
        } catch (MailAuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + e.getMessage());
        } catch (MailSendException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email: " + e.getMessage());
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error in email messaging: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error: " + e.getMessage());
        }
    }

    @PostMapping("/otp")
    public ResponseEntity<String> sendOTPToEmail(@RequestBody OTPRequest otpRequest) {
        otpUtil.generateOTP();
        String otp = otpUtil.getOtp();
        LocalDateTime time = otpUtil.getTime();
        LocalDateTime timeExpired = time.plusMinutes(3);

        String to = otpRequest.getEmail();
        String subject = otp+" là mã khôi phục tài khoản của bạn";

        User user = userRepository.findByEmail(to).orElseThrow(() -> new NotFoundException("User not found"));
        user.setOtp(otp);
        user.setOtpExpTime(timeExpired);
        userRepository.save(user);
        Context context = new Context();
        context.setVariable("otp", otp);
        context.setVariable("name", user.getName());
       //thoi gian het han se la 3 phut
        /// convert to format dd/MM/yyyy HH:mm:ss
        String timeFormat= timeExpired.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        context.setVariable("time", timeFormat);


        try {
            emailSenderService.sendOTPToEmail(otpRequest, subject, "email-template", context);
            return ResponseEntity.ok().body("Sent email successfully!");
        } catch (MailAuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + e.getMessage());
        } catch (MailSendException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error: " + e.getMessage());
        }
    }

}
