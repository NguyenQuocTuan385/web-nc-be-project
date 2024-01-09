package com.group6.ads.util;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Component
public class OtpUtil {
   // hãy viết hàm tạo 1 mã otp ngẫu nhiên có 6 chữ số và thời gian sống là 3 phút
    private String otp;
    private LocalDateTime time;

    public void generateOTP() {
        this.otp = String.valueOf((int) (Math.random() * (999999 - 100000 + 1) + 100000));
        this.time = LocalDateTime.now();
    }
}
