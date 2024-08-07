package com.emssanareps.affiliate.manager.dto.request;

import com.emssanareps.affiliate.manager.constants.AffiliateConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AffiliateRequest {
    
    @NotNull(message = AffiliateConstants.REQUIRED_IDENTIFICATION_NUMBER)
    private Long identificationNumber;

    @NotBlank(message = AffiliateConstants.REQUIRED_NAME)
    private String name;

    @NotBlank(message = AffiliateConstants.REQUIRED_LASTNAME)
    private String lastname;

    @NotNull(message = AffiliateConstants.REQUIRED_BIRTHDATE)
    private LocalDate birthdate;

    @NotNull(message = AffiliateConstants.REQUIRED_AFFILIATION_DATE)
    private LocalDate affiliationDate;

    @NotBlank(message = AffiliateConstants.REQUIRED_GENRE)
    private String genre;

    @NotBlank(message = AffiliateConstants.REQUIRED_CIVIL_STATUS)
    private String civilStatus;

    @NotBlank(message = AffiliateConstants.REQUIRED_REGIME)
    private String regime;
    
    private String status;

    @NotBlank(message = AffiliateConstants.REQUIRED_DOCUMENT_TYPE)
    private String documentType;

    @NotNull(message = AffiliateConstants.REQUIRED_LOCATION)
    private LocationRequest location;

    private List<AffiliateContactRequest> contacts;
    
    private List<BeneficiaryRequest> beneficiaries;

}
