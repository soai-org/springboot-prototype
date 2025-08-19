package com.human.team1.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PatientDTO {
    @JsonProperty("PatientID")
    private String patientID;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("BirthDate")
    private String birthDate;

    @JsonProperty("Sex")
    private String sex;

    @JsonProperty("StudyCount")
    private int studyCount;
}
