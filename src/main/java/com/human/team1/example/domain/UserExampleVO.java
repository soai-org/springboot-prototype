package com.human.team1.example.domain;

import lombok.*;

@NoArgsConstructor  // @NoArgsConstructor : 매개변수가 없는 기본 생성자 자동 생성
@AllArgsConstructor // @AllArgsConstructor : 모든 필드를 매개변수로 받는 생성자 자동 생성
@ToString           // @ToString : 객체를 출력할 때 toString() 메서드를 자동 생성 : ToString() → 객체를 문자열로 출력할 때 편리
@Getter             // @Getter : 모든 필드에 대한 get함수 생성(snake case적용 ex) getId()같은 것들이 자동 생성됨)
public class UserExampleVO {
    private String id;
    // Integer 사용 이유:
    // 1) VO/DTO는 DB 컬럼과 매핑되는데, DB의 숫자 타입 컬럼은 null을 가질 수 있음
    // 2) primitive int는 null을 가질 수 없으므로, DB에서 null값이 들어올 경우 오류 발생
    // 3) 따라서 null을 허용하려면 객체 타입 Integer 사용
    private Integer password;
    private String name;
    private Integer age;
}