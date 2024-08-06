package com.emssanareps.affiliate.manager.service.impl;

import com.emssanareps.affiliate.manager.dto.request.DocumentTypeRequest;
import com.emssanareps.affiliate.manager.dto.request.NameOrDescriptionRequest;
import com.emssanareps.affiliate.manager.dto.request.RequestDto;
import com.emssanareps.affiliate.manager.dto.response.DocumentTypeResponse;
import com.emssanareps.affiliate.manager.mapper.DocumentTypeMapper;
import com.emssanareps.affiliate.manager.model.DocumentType;
import com.emssanareps.affiliate.manager.repository.DocumentTypeRepository;
import com.emssanareps.affiliate.manager.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Page<DocumentTypeResponse> readAll(RequestDto<Object> requestDto) {
        Pageable pageable = PageRequest.of(requestDto.getPageNumber(), requestDto.getRowsNumber());
        return documentTypeMapper.toResponsePage(
                documentTypeRepository.findAll(
                        pageable
                )
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

    @Override
    public DocumentTypeResponse modify(Long documentTypeId, DocumentTypeRequest documentTypeRequest) {
        return documentTypeRepository.findById(documentTypeId)
                .map(documentType -> {
                    DocumentType updatedDocumentType = documentTypeMapper.toEntity(documentTypeRequest);
                    updatedDocumentType.setId(documentType.getId());

                    documentTypeRepository.save(updatedDocumentType);
                    return updatedDocumentType;
                })
                .map(documentTypeMapper::toResponse)
                .orElseThrow(
                        () -> new IllegalArgumentException("No se ha encontrado un tipo de documento con id ".concat(documentTypeId.toString()))
                );
    }

    @Override
    public void remove(Long documentTypeId) {
        documentTypeRepository.findById(documentTypeId)
                .orElseThrow(
                        () -> new IllegalArgumentException("No se ha encontrado un tipo de documento con id ".concat(documentTypeId.toString()))
                );

        documentTypeRepository.deleteById(documentTypeId);
    }

    @Override
    public Page<DocumentTypeResponse> searchNameOrDescription(RequestDto<NameOrDescriptionRequest> documentTypeRequest) {
        Pageable pageable = PageRequest.of(documentTypeRequest.getPageNumber(), documentTypeRequest.getRowsNumber());
        return documentTypeMapper.toResponsePage(
                documentTypeRepository.findByNameContainingIgnoreCaseOrDescriptionContainsIgnoreCase(
                        documentTypeRequest.getData().getName(),
                        documentTypeRequest.getData().getDescription(),
                        pageable
                )
        );
    }
}
