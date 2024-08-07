package com.emssanareps.affiliate.manager.dto.request;

import com.emssanareps.affiliate.manager.constants.BeneficiaryConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BeneficiaryRequest {

    @NotNull(message = BeneficiaryConstants.REQUIRED_IDENTIFICATION_NUMBER)
    private Long identificationNumber;

    @NotBlank(message = BeneficiaryConstants.REQUIRED_NAME)
    private String name;

    @NotBlank(message = BeneficiaryConstants.REQUIRED_LASTNAME)
    private String lastname;

    @NotNull(message = BeneficiaryConstants.REQUIRED_BIRTHDATE)
    private LocalDate birthdate;

    @NotNull(message = BeneficiaryConstants.REQUIRED_AFFILIATION_DATE)
    private LocalDate affiliationDate = LocalDate.now();

    @NotBlank(message = BeneficiaryConstants.REQUIRED_GENRE)
    private String genre;

    private String status;

    @NotBlank(message = BeneficiaryConstants.REQUIRED_DOCUMENT_TYPE)
    private String documentType;

    @NotBlank(message = BeneficiaryConstants.REQUIRED_BENEFICIARY_TYPE)
    private String beneficiaryType;

    @NotNull(message = BeneficiaryConstants.REQUIRED_LOCATION)
    private LocationRequest location;
}
