package com.human.team1.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // @Configuration : 이 클래스가 스프링 설정 파일임을 나타냄
public class WebMVCExampleConfigurer implements WebMvcConfigurer {  // WebMvcConfigurer 를 구현해서 MVC 관련 설정을 변경할 수 있도록 함
    // WebMvcConfigurer는 스프링에서 제공하는 인터페이스. 스프링 MVC동작을 커스터마이징할 수 있는 메서드들이 정의됨
    /*
    addCorsMappings(CorsRegistry registry) → CORS 정책 수정

    addViewControllers(ViewControllerRegistry registry) → 특정 URL을 특정 뷰에 매핑

    addInterceptors(InterceptorRegistry registry) → 요청/응답 가로채는 인터셉터 등록
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000");   // 리액트 서버포트 요청 허용
    }
}