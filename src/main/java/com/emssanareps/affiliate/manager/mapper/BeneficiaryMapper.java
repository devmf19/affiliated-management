package com.emssanareps.affiliate.manager.mapper;

import com.emssanareps.affiliate.manager.dto.request.BeneficiaryRequest;
import com.emssanareps.affiliate.manager.dto.response.BeneficiaryResponse;
import com.emssanareps.affiliate.manager.dto.response.BeneficiaryResponse;
import com.emssanareps.affiliate.manager.model.Beneficiary;
import com.emssanareps.affiliate.manager.model.Beneficiary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LocationMapper.class, EnumsMapper.class})
public interface BeneficiaryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "affiliateId", ignore = true)
    @Mapping(target = "genre", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "documentType", ignore = true)
    @Mapping(target = "beneficiaryType", ignore = true)
    @Mapping(target = "location", ignore = true)
    Beneficiary toEntity(BeneficiaryRequest beneficiaryRequest);

    BeneficiaryResponse toResponse(Beneficiary beneficiary);

    List<BeneficiaryResponse> toResponseList(List<Beneficiary> beneficiaryList);

    default Page<BeneficiaryResponse> toResponsePage(Page<Beneficiary> beneficiaryPage) {
        return beneficiaryPage.map(this::toResponse);
    }
}
