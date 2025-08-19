package com.human.team1.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data   // @Getter, @Setter, @EqualsAndHashCode, @RequiredArgsConstructor, @ToString 포함
@NoArgsConstructor
@AllArgsConstructor
public class UserExampleDTO {
    @NotBlank(message = "ID는 필수입니다.") // NotBlank는 문자열 전용
    private String id;
    @NotNull(message = "password는 필수입니다.")  // 숫자 경우에는 NotNull사용
    private Integer password;
    @NotBlank(message = "name은 필수입니다.")
    private String name;
    @NotNull(message = "age는 필수입니다.")
    private Integer age;
}