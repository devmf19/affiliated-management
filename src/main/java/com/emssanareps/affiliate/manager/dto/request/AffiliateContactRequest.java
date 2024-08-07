package com.emssanareps.affiliate.manager.dto.request;

import com.emssanareps.affiliate.manager.constants.ContactConstants;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AffiliateContactRequest {

    @NotBlank(message = ContactConstants.REQUIRED_CONTACT_TYPE)
    private String contactType;

    private String contact;
}
