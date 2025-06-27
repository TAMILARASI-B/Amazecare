package com.amazecare.service;

import com.amazecare.dto.PrescriptionDTO;
import org.springframework.http.ResponseEntity;

public interface DoctorService {
    ResponseEntity<?> getAppointments(Long doctorId);
    ResponseEntity<?> addPrescription(PrescriptionDTO dto);
    ResponseEntity<?> cancelAppointment(Long appointmentId);
}
