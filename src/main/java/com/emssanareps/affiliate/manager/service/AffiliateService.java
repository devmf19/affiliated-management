package com.emssanareps.affiliate.manager.service;

import com.emssanareps.affiliate.manager.dto.request.AffiliateRequest;
import com.emssanareps.affiliate.manager.dto.response.AffiliateResponse;

import java.util.List;

public interface AffiliateService {
    AffiliateResponse create(AffiliateRequest affiliateRequest);
    List<AffiliateResponse> readAll();

    AffiliateResponse readById(Long affiliateId);
}
