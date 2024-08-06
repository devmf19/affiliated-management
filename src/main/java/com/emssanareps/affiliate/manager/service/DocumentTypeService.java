package com.emssanareps.affiliate.manager.service;

import com.emssanareps.affiliate.manager.dto.request.DocumentTypeRequest;
import com.emssanareps.affiliate.manager.dto.request.NameOrDescriptionRequest;
import com.emssanareps.affiliate.manager.dto.request.RequestDto;
import com.emssanareps.affiliate.manager.dto.response.DocumentTypeResponse;
import org.springframework.data.domain.Page;

public interface DocumentTypeService {
    DocumentTypeResponse create(DocumentTypeRequest documentTypeRequest);

    Page<DocumentTypeResponse> readAll(RequestDto<Object> requestDto);

    DocumentTypeResponse readById(Long documentTypeId);

    DocumentTypeResponse modify(Long documentTypeId, DocumentTypeRequest documentTypeRequest);

    void remove(Long documentTypeId);

    Page<DocumentTypeResponse> searchNameOrDescription(RequestDto<NameOrDescriptionRequest> documentTypeRequest);
}
