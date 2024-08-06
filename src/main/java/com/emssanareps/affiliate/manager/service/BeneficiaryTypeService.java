package com.emssanareps.affiliate.manager.service;

import com.emssanareps.affiliate.manager.dto.request.BeneficiaryTypeRequest;
import com.emssanareps.affiliate.manager.dto.response.BeneficiaryTypeResponse;

import java.util.List;

public interface BeneficiaryTypeService {
    BeneficiaryTypeResponse create(BeneficiaryTypeRequest beneficiaryTypeRequest);

    List<BeneficiaryTypeResponse> readAll();

    BeneficiaryTypeResponse readById(Long beneficiaryTypeId);
}
