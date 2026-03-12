package com.cinesation.cinesation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling; // 👈 추가

@EnableScheduling // 👈 추가 (스케줄러 기능 활성화)
@SpringBootApplication
public class CinesationApplication {
    public static void main(String[] args) {
        SpringApplication.run(CinesationApplication.class, args);
    }
}