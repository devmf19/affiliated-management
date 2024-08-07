package com.emssanareps.affiliate.manager.service;

import com.emssanareps.affiliate.manager.dto.request.AffiliateContactRequest;
import com.emssanareps.affiliate.manager.dto.response.AffiliateContactResponse;

public interface ContactService {

    AffiliateContactResponse create(Long affiliateId, AffiliateContactRequest affiliateContactRequest);

    AffiliateContactResponse modify(Long affiliateId, AffiliateContactRequest affiliateContactRequest);

    void remove(Long AffiliateId, AffiliateContactRequest affiliateContactRequest);
}
