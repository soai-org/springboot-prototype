package com.human.team1.example.service;

import com.human.team1.example.dto.PatientDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class PatientService {

    @Value("${fastapi.endpoint}")
    private String fastApiEndpoint;

    public List<PatientDTO> selectPatientRequest(String name) throws UnsupportedEncodingException {
        RestTemplate restTemplate = new RestTemplate();

        String url = fastApiEndpoint + "?name=" + URLEncoder.encode(name, StandardCharsets.UTF_8);

        PatientDTO[] response = restTemplate.getForObject(url, PatientDTO[].class);

        return Arrays.asList(Objects.requireNonNull(response));
    }
}
