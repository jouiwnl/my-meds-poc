package com.wima.medicine.business;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wima.medicine.models.Doctor;
import com.wima.medicine.repositories.DoctorRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.print.Doc;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class DoctorBusiness {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DoctorRepository repository;

    private static final String ROOT_API = "https://api.infosimples.com/api/v2/consultas/cfm/cadastro?inscricao=%s&uf=%s&token=YGOdtOC7T8U3_OtNmKiV6W5kHBIYYPQl6LkeXwgt&timeout=300";

    public Doctor registerByCrm(Doctor doctor) {
        final String crm = doctor.getCrm();
        final String uf = doctor.getUf();
        var existsDoctor = this.repository.findByCrmAndUf(crm, uf);

        if (Objects.nonNull(existsDoctor)) {
            return null;
        }

        final String uri = String.format(ROOT_API, crm, uf);
        ResponseEntity<ValidateCrmResponse> response =
                this.restTemplate.postForEntity(uri, null, ValidateCrmResponse.class);

        if (response.getStatusCode().isError()) {
            return null;
        }

        ValidateCrmResponse body = response.getBody();

        if (Objects.isNull(body)) {
            return null;
        }

        String[] fullName = body.getData().get(0).getNome().split(" ");
        String firstName = Arrays.asList(fullName).get(0);
        String lastName = Arrays.asList(fullName).get(fullName.length - 1);

        return Doctor.builder()
                .firstName(firstName)
                .lastName(lastName)
                .crm(crm)
                .uf(uf)
                .email(doctor.getEmail())
                .password(doctor.getPassword())
                .build();
    }
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class ValidateCrmResponse {
    private String code;
    private List<ValidateCrmDataResponse> data;
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class ValidateCrmDataResponse {
    private String nome;

}
