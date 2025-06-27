package com.amazecare.controller;

import com.amazecare.dto.AppointmentDTO;
import com.amazecare.dto.PrescriptionDTO;
import com.amazecare.service.DoctorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/appointments/{doctorId}")
    public ResponseEntity<?> getAppointments(@PathVariable Long doctorId) {
        return doctorService.getAppointments(doctorId);
    }

    @PostMapping("/prescribe")
    public ResponseEntity<?> prescribe(@RequestBody PrescriptionDTO dto) {
        return doctorService.addPrescription(dto);
    }

    @PutMapping("/cancel/{appointmentId}")
    public ResponseEntity<?> cancelAppointment(@PathVariable Long appointmentId) {
        return doctorService.cancelAppointment(appointmentId);
    }
}
