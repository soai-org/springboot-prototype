package com.human.team1.example.service;

import com.human.team1.example.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrthanCService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${orthanc.endpoint}")
    private String orthancEndpoint;

    @Value("${orthanc.id}")
    private String orthancID;
    @Value("${orthanc.password}")
    private String orthancPassword;

    public String getPatient(String name) {
        String url = orthancEndpoint + "/tools/find";

        Map<String, Object> body = new HashMap<>();
        body.put("Level", "Patient");
        body.put("Query", Map.of("PatientName", name));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String auth = orthancID + ":" + orthancPassword;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        return response.getBody();
    }
}
