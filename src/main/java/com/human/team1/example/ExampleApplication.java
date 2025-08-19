package com.human.team1.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
// → 스프링 부트 프로젝트의 시작점임을 나타내는 어노테이션
// → 내부적으로 아래 3가지 기능을 합쳐놓은 것과 동일
//    1. @Configuration : 스프링 설정 클래스임을 표시
//    2. @EnableAutoConfiguration : 스프링 부트 자동 설정 기능 활성화
//    3. @ComponentScan : 같은 패키지와 하위 패키지의 @Component, @Service, @Controller 등을 자동 스캔
@SpringBootApplication
public class ExampleApplication {
    public static void main(String[] args) {
        // SpringApplication.run(...)
        // → 스프링 부트 애플리케이션 실행
        // → 내장 톰캣 서버 실행, 스프링 컨테이너 생성, 모든 Bean 등록 및 초기화 수행
        SpringApplication.run(ExampleApplication.class, args);
    }
}
