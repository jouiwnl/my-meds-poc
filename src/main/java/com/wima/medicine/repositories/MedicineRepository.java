package com.wima.medicine.repositories;

import com.wima.medicine.models.Medicine;
import com.wima.medicine.models.Pacient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MedicineRepository extends MongoRepository<Medicine, String> {

    public List<Medicine> findByPacientId(String pacientId);

}
