package com.amazecare.controller;

import com.amazecare.dto.DoctorDTO;
import com.amazecare.dto.PatientDTO;
import com.amazecare.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/add-doctor")
    public ResponseEntity<?> addDoctor(@RequestBody DoctorDTO doctorDTO) {
        return adminService.addDoctor(doctorDTO);
    }

    @PostMapping("/add-patient")
    public ResponseEntity<?> addPatient(@RequestBody PatientDTO patientDTO) {
        return adminService.addPatient(patientDTO);
    }

    @DeleteMapping("/delete-doctor/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long id) {
        return adminService.deleteDoctor(id);
    }

    @DeleteMapping("/delete-patient/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        return adminService.deletePatient(id);
    }
}
