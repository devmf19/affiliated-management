package com.emssanareps.affiliate.manager.mapper;

import com.emssanareps.affiliate.manager.dto.request.DocumentTypeRequest;
import com.emssanareps.affiliate.manager.dto.response.DocumentTypeResponse;
import com.emssanareps.affiliate.manager.model.DocumentType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentTypeMapper {

    @Mapping(target = "id", ignore = true)
    DocumentType toEntity (DocumentTypeRequest documentTypeRequest);

    DocumentTypeResponse toResponse(DocumentType documentType);

    List<DocumentTypeResponse> toResponseList(List<DocumentType> documentTypePage);

    default Page<DocumentTypeResponse> toResponsePage(Page<DocumentType> documentTypePage) {
        return documentTypePage.map(this::toResponse);
    }
}
