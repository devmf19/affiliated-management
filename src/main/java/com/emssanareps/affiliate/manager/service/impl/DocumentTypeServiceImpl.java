package com.emssanareps.affiliate.manager.service.impl;

import com.emssanareps.affiliate.manager.dto.request.DocumentTypeRequest;
import com.emssanareps.affiliate.manager.dto.response.DocumentTypeResponse;
import com.emssanareps.affiliate.manager.mapper.DocumentTypeMapper;
import com.emssanareps.affiliate.manager.repository.DocumentTypeRepository;
import com.emssanareps.affiliate.manager.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private final DocumentTypeRepository documentTypeRepository;
    private final DocumentTypeMapper documentTypeMapper;

    @Autowired
    public DocumentTypeServiceImpl(DocumentTypeRepository documentTypeRepository, DocumentTypeMapper documentTypeMapper) {
        this.documentTypeRepository = documentTypeRepository;
        this.documentTypeMapper = documentTypeMapper;
    }

    @Override
    public DocumentTypeResponse create(DocumentTypeRequest documentTypeRequest) {
        return documentTypeMapper.toResponse(
                documentTypeRepository.save(
                        documentTypeMapper.toEntity(documentTypeRequest)
                )
        );
    }

    @Override
    public List<DocumentTypeResponse> readAll() {
        return documentTypeMapper.toResponseList(
                documentTypeRepository.findAll()
        );
    }

    @Override
    public DocumentTypeResponse readById(Long documentTypeId) {
        return documentTypeRepository.findById(documentTypeId)
                .map(documentTypeMapper::toResponse)
                .orElseThrow(
                        () -> new IllegalArgumentException("No se ha encontrado un tipo de documento con id ".concat(documentTypeId.toString()))
                );
    }
}
