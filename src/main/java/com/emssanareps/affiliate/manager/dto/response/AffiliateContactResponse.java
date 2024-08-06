package com.emssanareps.affiliate.manager.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AffiliateContactResponse {
    private Long id;
    private String contactType;
    private String contact;
}
