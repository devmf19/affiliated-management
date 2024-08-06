package com.emssanareps.affiliate.manager.mapper;

import com.emssanareps.affiliate.manager.dto.request.ContactTypeRequest;
import com.emssanareps.affiliate.manager.dto.response.ContactTypeResponse;
import com.emssanareps.affiliate.manager.dto.response.ContactTypeResponse;
import com.emssanareps.affiliate.manager.model.ContactType;
import com.emssanareps.affiliate.manager.model.ContactType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactTypeMapper {

    @Mapping(target = "id", ignore = true)
    ContactType toEntity (ContactTypeRequest contactTypeRequest);

    ContactTypeResponse toResponse(ContactType contactType);

    List<ContactTypeResponse> toResponseList(List<ContactType> contactTypeList);

    default Page<ContactTypeResponse> toResponsePage(Page<ContactType> contactTypePage) {
        return contactTypePage.map(this::toResponse);
    }
}
