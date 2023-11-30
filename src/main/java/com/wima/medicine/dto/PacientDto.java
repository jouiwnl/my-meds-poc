package com.wima.medicine.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wima.medicine.models.Doctor;
import com.wima.medicine.models.Pacient;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacientDto {
    private String id;
    private String firstName;
    private String lastName;
    private String doctorId;

    public static PacientDto fromEntity(Pacient entity) {
        return PacientDto.builder()
            .id(entity.getId())
            .firstName(entity.getFirstName())
            .lastName(entity.getLastName())
            .doctorId(entity.getDoctorId())
            .build();
    }
}
