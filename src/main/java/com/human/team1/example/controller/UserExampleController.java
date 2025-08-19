package com.human.team1.example.controller;

import com.human.team1.example.dto.UserExampleDTO;
import com.human.team1.example.domain.UserExampleVO;
import com.human.team1.example.dto.PatientDTO;
import com.human.team1.example.service.PatientService;
import com.human.team1.example.service.UserExampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Slf4j  // @Slf4j : 로그(log) 기능을 자동으로 추가해주는 Lombok 어노테이션 (log.info, log.error 등 사용 가능)
@RestController // @RestController : 이 클래스가 REST API 요청을 처리하는 컨트롤러임을 표시
@RequestMapping("/api/users")   // @RequestMapping("/api/users") : 이 컨트롤러의 모든 메서드 URL 앞에 "/api/users"가 공통적으로 붙음
@RequiredArgsConstructor    // @RequiredArgsConstructor : final로 선언된 필드를 자동으로 생성자 주입해주는 Lombok 어노테이션
public class UserExampleController {
    // final → 반드시 생성자를 통해 주입되는 필드
    private final UserExampleService userExampleService;
    private final PatientService patientService;

    @GetMapping // @GetMapping : HTTP GET 요청을 처리 -> /api/users
    public ResponseEntity<List<UserExampleVO>> getUsers() {
        List<UserExampleVO> users = userExampleService.getAllUsers();
        log.info("모든 사용자 조회: {}", users.size());

        // ResponseEntity.ok(users)
        // → ResponseEntity는 HTTP 응답 전체(상태코드 + 헤더 + 바디)를 표현하는 객체
        // → ok()는 HTTP 상태코드 200(성공)을 의미하며, 괄호 안에 넣은 객체(users)가 JSON으로 변환되어 응답 본문(Body)에 담김
        return ResponseEntity.ok(users); // JSON 반환
    }

    // @GetMapping("/{id}") : "/api/users/{id}" 형태의 요청을 처리
    // {id}는 경로 변수(PathVariable)로 들어오는 값 (예: /api/users/user01)
    @GetMapping("/{id}")
    public ResponseEntity<UserExampleVO> getUser(@PathVariable String id) { // @PathVariable : URL 경로에 있는 {id} 값을 메서드 파라미터(id)로 전달받음
        UserExampleVO user = userExampleService.getUserById(id);
        return ResponseEntity.ok(user); // JSON 반환
    }

    // @PostMapping : HTTP POST 요청을 처리
    // 클라이언트에서 JSON 데이터를 전송하면 @RequestBody로 DTO에 매핑됨
    @PostMapping
    public ResponseEntity<UserExampleVO> createUser(@Validated @RequestBody UserExampleDTO userDTO) {
        // @Validated: 전달받은 userDTO의 유효성(Validation) 검증을 수행(@NotNull이나 @Min(0)이런 것들)
        // @RequestBody : HTTP 요청의 Body(JSON)를 자바 객체(UserExampleDTO)로 변환
        UserExampleVO user = new UserExampleVO(
                userDTO.getId(),
                userDTO.getPassword(),
                userDTO.getName(),
                userDTO.getAge()
        );
        UserExampleVO created = userExampleService.createUser(user);
        log.info("사용자 생성: {}", created);
        return ResponseEntity.ok(created); // JSON 반환
    }

    @GetMapping("/patients")
    public ResponseEntity<List<PatientDTO>> selectPatient(@RequestParam String name) throws UnsupportedEncodingException {
        List<PatientDTO> patients = patientService.selectPatientRequest(name);
        return ResponseEntity.ok(patients);
    }

}