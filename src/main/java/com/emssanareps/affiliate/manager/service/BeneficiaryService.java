package com.emssanareps.affiliate.manager.service;

import com.emssanareps.affiliate.manager.dto.request.BeneficiaryRequest;
import com.emssanareps.affiliate.manager.dto.request.BeneficiaryTypeRequest;
import com.emssanareps.affiliate.manager.dto.response.BeneficiaryResponse;
import com.emssanareps.affiliate.manager.dto.response.BeneficiaryTypeResponse;
import com.emssanareps.affiliate.manager.model.Beneficiary;

import java.util.List;

public interface BeneficiaryService {
    BeneficiaryResponse create(Long affiliateId, BeneficiaryRequest beneficiaryRequest);
}
