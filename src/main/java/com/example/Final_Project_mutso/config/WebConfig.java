package com.example.Final_Project_mutso.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(final CorsRegistry registry){
        registry.addMapping("/**");
    }
//    @Override
//    public void addCorsMappings(final CorsRegistry registry) {
//        registry.addMapping("http://localhost:3000")
////                .allowedOrigins("http://localhost:3000") // 허용할 origin을 설정합니다.
//                .allowedMethods("POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드를 설정합니다.
//                .allowedHeaders("Authorization") // 허용할 요청 헤더를 설정합니다.
//                .allowCredentials(true); // 인증 정보 허용 여부를 설정합니다.
//    }


    // js 파일 연결
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
    }
}
