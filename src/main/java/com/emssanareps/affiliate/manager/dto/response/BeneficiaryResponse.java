package com.emssanareps.affiliate.manager.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BeneficiaryResponse {
    private Long id;
    private String beneficiaryType;
    private String documentType;
    private Long identificationNumber;
    private String name;
    private String lastname;
    private LocalDate birthdate;
    private LocalDate affiliation_date;
    private String genre;
    private String status;
    private LocationResponse location;
}
