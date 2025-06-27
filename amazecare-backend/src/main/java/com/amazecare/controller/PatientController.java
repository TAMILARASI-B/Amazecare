package com.amazecare.controller;

import com.amazecare.dto.AppointmentDTO;
import com.amazecare.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/book")
    public ResponseEntity<?> bookAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        return patientService.bookAppointment(appointmentDTO);
    }

    @GetMapping("/appointments/{patientId}")
    public ResponseEntity<?> viewAppointments(@PathVariable Long patientId) {
        return patientService.viewAppointments(patientId);
    }

    @GetMapping("/history/{patientId}")
    public ResponseEntity<?> viewMedicalHistory(@PathVariable Long patientId) {
        return patientService.getMedicalHistory(patientId);
    }
}
