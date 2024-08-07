package com.emssanareps.affiliate.manager.mapper;

import com.emssanareps.affiliate.manager.dto.request.AffiliateContactRequest;
import com.emssanareps.affiliate.manager.dto.response.AffiliateContactResponse;
import com.emssanareps.affiliate.manager.model.AffiliateContact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EnumsMapper.class})
public interface AffiliateContactMapper {

    @Mapping(target = "affiliateId", ignore = true)
    @Mapping(target = "contactType", ignore = true)
    AffiliateContact toEntity (AffiliateContactRequest affiliateContactRequest);

    AffiliateContactResponse toResponse(AffiliateContact affiliateContact);

    List<AffiliateContactResponse> toResponseList(List<AffiliateContact> affiliateContactList);
}
