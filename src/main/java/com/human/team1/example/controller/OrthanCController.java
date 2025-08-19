package com.human.team1.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.human.team1.example.dto.PatientDTO;
import com.human.team1.example.service.OrthanCService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orthanc")
public class OrthanCController {
    private final OrthanCService orthancService;

    @PostMapping("/patient")
    public String getPatient(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        return orthancService.getPatient(name); // JSON 문자열 그대로 반환
    }
}
