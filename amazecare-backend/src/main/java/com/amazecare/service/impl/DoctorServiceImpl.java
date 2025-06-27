package com.amazecare.service.impl;

import com.amazecare.dto.PrescriptionDTO;
import com.amazecare.entity.*;
import com.amazecare.repository.*;
import com.amazecare.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final AppointmentRepository appointmentRepo;
    private final PrescriptionRepository prescriptionRepo;
    private final MedicalRecordRepository medicalRecordRepo;

    @Override
    public ResponseEntity<?> getAppointments(Long doctorId) {
        return ResponseEntity.ok(appointmentRepo.findByDoctorId(doctorId));
    }

    @Override
    public ResponseEntity<?> addPrescription(PrescriptionDTO dto) {
        Appointment appointment = appointmentRepo.findById(dto.getAppointmentId()).orElseThrow();

        MedicalRecord record = new MedicalRecord();
        record.setAppointment(appointment);
        record.setDiagnosis(dto.getDiagnosis());
        record.setTreatmentPlan(dto.getTreatmentPlan());
        record.setRecommendedTests(dto.getRecommendedTests());
        medicalRecordRepo.save(record);

        Prescription prescription = new Prescription();
        prescription.setAppointment(appointment);
        prescription.setMedicineDetails(dto.getPrescribedMedicine());
        prescriptionRepo.save(prescription);

        appointment.setStatus("COMPLETED");
        appointmentRepo.save(appointment);

        return ResponseEntity.ok("Consultation completed and prescription saved.");
    }

    @Override
    public ResponseEntity<?> cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId).orElseThrow();
        appointment.setStatus("CANCELLED");
        appointmentRepo.save(appointment);
        return ResponseEntity.ok("Appointment cancelled.");
    }
}
