package com.wima.medicine.controllers;

import com.wima.medicine.dto.DoctorDto;
import com.wima.medicine.dto.PacientDto;
import com.wima.medicine.models.Doctor;
import com.wima.medicine.models.Medicine;
import com.wima.medicine.models.Pacient;
import com.wima.medicine.repositories.DoctorRepository;
import com.wima.medicine.repositories.MedicineRepository;
import com.wima.medicine.repositories.PacientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pacients")
public class PacientController {

    @Autowired
    private PacientRepository repository;

    @Autowired
    private MedicineRepository medicineRepository;

    @GetMapping
    public ResponseEntity<List<PacientDto>> getAll() {
        return ResponseEntity.ok(
            this.repository.findAll()
                .stream()
                .map(PacientDto::fromEntity)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}/medicines")
    public ResponseEntity<List<Medicine>> findAllByMedicinesByPacient(@PathVariable("id") String pacientId) {
        return ResponseEntity.ok(this.medicineRepository.findByPacientId(pacientId));
    }

    @PostMapping
    public ResponseEntity<PacientDto> create(@RequestBody Pacient pacient) {
        final Pacient saved = this.repository.save(pacient);
        return ResponseEntity.ok(PacientDto.fromEntity(saved));
    }

}
