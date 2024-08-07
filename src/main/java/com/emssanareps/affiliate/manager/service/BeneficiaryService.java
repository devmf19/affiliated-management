package com.emssanareps.affiliate.manager.service;

import com.emssanareps.affiliate.manager.dto.request.*;
import com.emssanareps.affiliate.manager.dto.response.BeneficiaryResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BeneficiaryService {
    BeneficiaryResponse create(Long affiliateId, BeneficiaryRequest beneficiaryRequest);

    Page<BeneficiaryResponse> readAll(RequestDto<Object> requestDto);

    BeneficiaryResponse readById(Long beneficiaryId);

    BeneficiaryResponse modify(Long beneficiaryId, BeneficiaryRequest beneficiaryRequest);

    void remove(Long beneficiaryId);

    Page<BeneficiaryResponse> searchNameOrDescription(RequestDto<NameOrLastnameRequest> beneficiaryRequest);
}
