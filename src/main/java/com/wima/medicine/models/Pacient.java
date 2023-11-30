package com.wima.medicine.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "pacients")
public class Pacient {

    @Id
    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String doctorId;

}
