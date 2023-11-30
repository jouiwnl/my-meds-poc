package com.wima.medicine.repositories;

import com.wima.medicine.models.Doctor;
import com.wima.medicine.models.Pacient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PacientRepository extends MongoRepository<Pacient, String> {

    public List<Pacient> findByDoctorId(String doctorId);

}
