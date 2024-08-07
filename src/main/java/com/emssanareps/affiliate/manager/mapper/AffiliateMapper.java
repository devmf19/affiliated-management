package com.emssanareps.affiliate.manager.mapper;

import com.emssanareps.affiliate.manager.dto.request.AffiliateRequest;
import com.emssanareps.affiliate.manager.dto.response.AffiliateResponse;
import com.emssanareps.affiliate.manager.model.Affiliate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LocationMapper.class, BeneficiaryMapper.class})
public interface AffiliateMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "genre", ignore = true)
    @Mapping(target = "regime", ignore = true)
    @Mapping(target = "civilStatus", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "documentType", ignore = true)
    @Mapping(target = "location", ignore = true)
    @Mapping(target = "beneficiaries", ignore = true)
    @Mapping(target = "contacts", ignore = true)
    Affiliate toEntity(AffiliateRequest affiliateRequest);

    @Mapping(target = "documentType", expression = "java(affiliate.getDocumentType.name())")
    @Mapping(target = "genre",expression = "java(affiliate.getGenre.name())")
    @Mapping(target = "civilStatus", expression = "java(affiliate.getCivilStatus.name())")
    @Mapping(target = "regime", expression = "java(affiliate.getRegime.name())")
    @Mapping(target = "status", expression = "java(affiliate.getStatus.name())")
    AffiliateResponse toResponse(Affiliate affiliate);

    List<AffiliateResponse> toResponseList(List<Affiliate> affiliateList);

    default Page<AffiliateResponse> toResponsePage(Page<Affiliate> affiliatePage) {
        return affiliatePage.map(this::toResponse);
    }
}
