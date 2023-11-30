package com.wima.medicine.repositories;

import com.wima.medicine.models.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DoctorRepository extends MongoRepository<Doctor, String> {

    public Doctor findByFirstName(String firstName);
    public List<Doctor> findByLastName(String lastName);
    public Doctor findByCrmAndUf(String crm, String uf);

}
