package com.wima.medicine.controllers;

import com.wima.medicine.dto.PacientDto;
import com.wima.medicine.models.Medicine;
import com.wima.medicine.models.Pacient;
import com.wima.medicine.repositories.MedicineRepository;
import com.wima.medicine.repositories.PacientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medicines")
public class MedicineController {

    @Autowired
    private MedicineRepository repository;

    @GetMapping
    public ResponseEntity<List<Medicine>> getAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Medicine> create(@RequestBody Medicine medicine) {
        return ResponseEntity.ok(this.repository.save(medicine));
    }

}
