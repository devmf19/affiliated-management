package com.emssanareps.affiliate.manager.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BeneficiaryRequest {
    private Long beneficiaryTypeId;
    private Long documentTypeId;
    private Long identificationNumber;
    private String name;
    private String lastname;
    private LocalDate birthdate;
    private LocalDate affiliation_date;
    private String genre;
    private String status;
    private String documentType;
    private String beneficiaryType;
    private LocationRequest location;
}
