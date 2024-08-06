package com.emssanareps.affiliate.manager.mapper;

import com.emssanareps.affiliate.manager.dto.request.BeneficiaryTypeRequest;
import com.emssanareps.affiliate.manager.dto.response.BeneficiaryTypeResponse;
import com.emssanareps.affiliate.manager.model.BeneficiaryType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BeneficiaryTypeMapper {

    @Mapping(target = "id", ignore = true)
    BeneficiaryType toEntity (BeneficiaryTypeRequest beneficiaryTypeRequest);

    BeneficiaryTypeResponse toResponse(BeneficiaryType beneficiaryType);

    List<BeneficiaryTypeResponse> toResponseList(List<BeneficiaryType> beneficiaryTypeList);
}

