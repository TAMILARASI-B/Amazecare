package com.amazecare.service;

import com.amazecare.dto.DoctorDTO;
import com.amazecare.dto.PatientDTO;
import org.springframework.http.ResponseEntity;

public interface AdminService {
    ResponseEntity<?> addDoctor(DoctorDTO doctorDTO);
    ResponseEntity<?> addPatient(PatientDTO patientDTO);
    ResponseEntity<?> deleteDoctor(Long id);
    ResponseEntity<?> deletePatient(Long id);
}
