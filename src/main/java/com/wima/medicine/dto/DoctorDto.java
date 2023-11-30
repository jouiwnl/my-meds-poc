package com.wima.medicine.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wima.medicine.models.Doctor;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorDto {
    private String id;
    private String firstName;
    private String lastName;
    private String crm;
    private String uf;

    public static DoctorDto fromEntity(Doctor entity) {
        return DoctorDto.builder()
            .id(entity.getId())
            .firstName(entity.getFirstName())
            .lastName(entity.getLastName())
            .crm(entity.getCrm())
            .uf(entity.getUf())
            .build();
    }
}
