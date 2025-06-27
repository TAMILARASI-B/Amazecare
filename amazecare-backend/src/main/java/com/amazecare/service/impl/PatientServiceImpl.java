package com.amazecare.service.impl;

import com.amazecare.dto.AppointmentDTO;
import com.amazecare.entity.Appointment;
import com.amazecare.entity.Doctor;
import com.amazecare.entity.Patient;
import com.amazecare.repository.AppointmentRepository;
import com.amazecare.repository.DoctorRepository;
import com.amazecare.repository.PatientRepository;
import com.amazecare.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final AppointmentRepository appointmentRepo;
    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;

    @Override
    public ResponseEntity<?> bookAppointment(AppointmentDTO dto) {
        Doctor doctor = doctorRepo.findById(dto.getDoctorId()).orElseThrow();
        Patient patient = patientRepo.findById(dto.getPatientId()).orElseThrow();

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setSymptoms(dto.getSymptoms());
        appointment.setAppointmentDateTime(dto.getPreferredDateTime());
        appointment.setStatus("PENDING");

        appointmentRepo.save(appointment);
        return ResponseEntity.ok("Appointment booked successfully.");
    }

    @Override
    public ResponseEntity<?> viewAppointments(Long patientId) {
        return ResponseEntity.ok(appointmentRepo.findByPatientId(patientId));
    }

    @Override
    public ResponseEntity<?> getMedicalHistory(Long patientId) {
        List<Appointment> completed = appointmentRepo.findByPatientId(patientId)
                .stream().filter(a -> "COMPLETED".equals(a.getStatus())).toList();
        return ResponseEntity.ok(completed);
    }
}
