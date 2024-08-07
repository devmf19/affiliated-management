package com.emssanareps.affiliate.manager.service;

import com.emssanareps.affiliate.manager.dto.request.AffiliateRequest;
import com.emssanareps.affiliate.manager.dto.request.NameOrLastnameRequest;
import com.emssanareps.affiliate.manager.dto.request.RequestDto;
import com.emssanareps.affiliate.manager.dto.response.AffiliateResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AffiliateService {
    AffiliateResponse create(AffiliateRequest affiliateRequest);

    Page<AffiliateResponse> readAll(RequestDto<Object> requestDto);

    AffiliateResponse readById(Long affiliateId);

    AffiliateResponse modify(Long affiliateId, AffiliateRequest affiliateRequest);

    void remove(Long affiliateId);

    Page<AffiliateResponse> searchNameOrDescription(RequestDto<NameOrLastnameRequest> affiliateRequest);
}
