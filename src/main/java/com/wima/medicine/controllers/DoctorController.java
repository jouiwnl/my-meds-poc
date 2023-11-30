package com.wima.medicine.controllers;

import com.wima.medicine.business.DoctorBusiness;
import com.wima.medicine.dto.DoctorDto;
import com.wima.medicine.dto.PacientDto;
import com.wima.medicine.models.Doctor;
import com.wima.medicine.repositories.DoctorRepository;
import com.wima.medicine.repositories.PacientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @Autowired
    private PacientRepository pacientRepository;

    @Autowired
    private DoctorBusiness doctorBusiness;

    @GetMapping
    public ResponseEntity<List<DoctorDto>> getAll() {
        return ResponseEntity.ok(
            this.repository.findAll()
                .stream()
                .map(DoctorDto::fromEntity)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}/pacients")
    public ResponseEntity<List<PacientDto>> findAllByPacientsByDoctor(@PathVariable("id") String doctorId) {
        return ResponseEntity.ok(
                this.pacientRepository.findByDoctorId(doctorId)
                        .stream()
                        .map(PacientDto::fromEntity)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Doctor doctor) {
        final Doctor validated = this.doctorBusiness.registerByCrm(doctor);
        if (Objects.isNull(validated)) {
            return ResponseEntity.badRequest().body("Falha ao validar CRM ou registro em duplicidade");
        }
        final Doctor saved = this.repository.save(validated);
        return ResponseEntity.ok(DoctorDto.fromEntity(saved));
    }

}
