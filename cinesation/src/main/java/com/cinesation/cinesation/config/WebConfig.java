package com.cinesation.cinesation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 우리 서버의 모든 API 주소에 대해서
                .allowedOrigins("http://localhost:3000") // 👈 Next.js 서버 주소만 특별히 허용!
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 요청 방식들
                .allowedHeaders("*") // 모든 헤더 허용
                .allowCredentials(true); // 쿠키나 인증 정보도 같이 보낼 수 있게 허용
    }
}