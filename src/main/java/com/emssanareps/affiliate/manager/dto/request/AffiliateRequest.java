package com.emssanareps.affiliate.manager.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AffiliateRequest {
    private Long documentTypeId;
    private Long identificationNumber;
    private String name;
    private String lastname;
    private LocalDate birthdate;
    private LocalDate affiliation_date;
    private String genre;
    private String civilStatus;
    private String regime;
    private LocationRequest location;
    private List<AffiliateContactRequest> contacts;
    private List<BeneficiaryRequest> beneficiaries;

}
