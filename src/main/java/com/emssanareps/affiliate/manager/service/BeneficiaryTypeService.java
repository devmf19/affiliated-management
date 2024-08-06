package com.emssanareps.affiliate.manager.service;

import com.emssanareps.affiliate.manager.dto.request.BeneficiaryTypeRequest;
import com.emssanareps.affiliate.manager.dto.request.NameOrDescriptionRequest;
import com.emssanareps.affiliate.manager.dto.request.RequestDto;
import com.emssanareps.affiliate.manager.dto.response.BeneficiaryTypeResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BeneficiaryTypeService {
    BeneficiaryTypeResponse create(BeneficiaryTypeRequest beneficiaryTypeRequest);

    Page<BeneficiaryTypeResponse> readAll(RequestDto<Object> requestDto);

    BeneficiaryTypeResponse readById(Long beneficiaryTypeId);

    BeneficiaryTypeResponse modify(Long beneficiaryTypeId, BeneficiaryTypeRequest beneficiaryTypeRequest);

    void remove(Long beneficiaryTypeId);

    Page<BeneficiaryTypeResponse> searchNameOrDescription(RequestDto<NameOrDescriptionRequest> beneficiaryTypeRequest);
}
