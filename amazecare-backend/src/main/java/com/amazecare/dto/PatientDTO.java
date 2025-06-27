package com.amazecare.dto;

import lombok.Data;

@Data
public class PatientDTO {
    private String fullName;
    private String gender;
    private String dob;
    private String contactNumber;
    private String healthIssue;
}
