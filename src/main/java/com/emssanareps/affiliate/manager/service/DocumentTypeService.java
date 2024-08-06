package com.emssanareps.affiliate.manager.service;

import com.emssanareps.affiliate.manager.dto.request.DocumentTypeRequest;
import com.emssanareps.affiliate.manager.dto.response.DocumentTypeResponse;

import java.util.List;

public interface DocumentTypeService {
    DocumentTypeResponse create(DocumentTypeRequest documentTypeRequest);

    List<DocumentTypeResponse> readAll();

    DocumentTypeResponse readById(Long documentTypeId);
}
