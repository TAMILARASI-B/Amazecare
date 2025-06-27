package com.amazecare.service.impl;

import com.amazecare.dto.DoctorDTO;
import com.amazecare.dto.PatientDTO;
import com.amazecare.entity.Doctor;
import com.amazecare.entity.Patient;
import com.amazecare.repository.DoctorRepository;
import com.amazecare.repository.PatientRepository;
import com.amazecare.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;

    @Override
    public ResponseEntity<?> addDoctor(DoctorDTO dto) {
        Doctor doctor = new Doctor();
        doctor.setName(dto.getName());
        doctor.setSpecialty(dto.getSpecialty());
        doctor.setExperience(dto.getExperience());
        doctor.setQualification(dto.getQualification());
        doctor.setDesignation(dto.getDesignation());
        doctorRepo.save(doctor);
        return ResponseEntity.ok("Doctor added successfully.");
    }

    @Override
    public ResponseEntity<?> addPatient(PatientDTO dto) {
        Patient patient = new Patient();
        patient.setFullName(dto.getFullName());
        patient.setGender(dto.getGender());
        patient.setDob(dto.getDob());
        patient.setContactNumber(dto.getContactNumber());
        patient.setHealthIssue(dto.getHealthIssue());
        patientRepo.save(patient);
        return ResponseEntity.ok("Patient added successfully.");
    }

    @Override
    public ResponseEntity<?> deleteDoctor(Long id) {
        doctorRepo.deleteById(id);
        return ResponseEntity.ok("Doctor deleted.");
    }

    @Override
    public ResponseEntity<?> deletePatient(Long id) {
        patientRepo.deleteById(id);
        return ResponseEntity.ok("Patient deleted.");
    }
}
