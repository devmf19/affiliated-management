package com.emssanareps.affiliate.manager.service;

import com.emssanareps.affiliate.manager.dto.request.AffiliateContactRequest;
import com.emssanareps.affiliate.manager.dto.response.AffiliateContactResponse;

public interface AffiliateContactService {
    AffiliateContactResponse create(Long AffiliateId, AffiliateContactRequest affiliateContactRequest);
}
