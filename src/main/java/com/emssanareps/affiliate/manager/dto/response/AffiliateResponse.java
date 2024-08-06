package com.emssanareps.affiliate.manager.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AffiliateResponse {
    private Long id;
    private String documentType;
    private Long identificationNumber;
    private String name;
    private String lastname;
    private LocalDate birthdate;
    private LocalDate affiliation_date;
    private String genre;
    private String civilStatus;
    private String regime;
    private String status;
    private LocationResponse location;
    private List<AffiliateContactResponse> contacts;
    private List<BeneficiaryResponse> beneficiaries;
}
