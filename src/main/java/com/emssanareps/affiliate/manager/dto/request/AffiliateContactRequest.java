package com.emssanareps.affiliate.manager.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AffiliateContactRequest {
    private Long contactTypeId;
    private String contact;
}
