package com.group6.ads.controllers.email;

import com.group6.ads.controllers.email.models.EmailSenderRequest;
import com.group6.ads.services.email.EmailSenderService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/email/")
@CrossOrigin("*")
public class EmailSenderController {
    @Autowired
    private EmailSenderService emailSenderService;

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

}
