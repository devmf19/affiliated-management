package com.emssanareps.affiliate.manager.service.impl;

import com.emssanareps.affiliate.manager.dto.request.ContactTypeRequest;
import com.emssanareps.affiliate.manager.dto.request.NameOrDescriptionRequest;
import com.emssanareps.affiliate.manager.dto.request.RequestDto;
import com.emssanareps.affiliate.manager.dto.response.ContactTypeResponse;
import com.emssanareps.affiliate.manager.mapper.ContactTypeMapper;
import com.emssanareps.affiliate.manager.model.ContactType;
import com.emssanareps.affiliate.manager.repository.ContactTypeRepository;
import com.emssanareps.affiliate.manager.service.ContactTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContactTypeServiceImpl implements ContactTypeService {
    private final ContactTypeRepository contactTypeRepository;
    private final ContactTypeMapper contactTypeMapper;

    @Autowired
    public ContactTypeServiceImpl(ContactTypeRepository contactTypeRepository, ContactTypeMapper contactTypeMapper) {
        this.contactTypeRepository = contactTypeRepository;
        this.contactTypeMapper = contactTypeMapper;
    }

    @Override
    public ContactTypeResponse create(ContactTypeRequest contactTypeRequest) {
        return contactTypeMapper.toResponse(
                contactTypeRepository.save(
                        contactTypeMapper.toEntity(contactTypeRequest)
                )
        );
    }

    @Override
    public Page<ContactTypeResponse> readAll(RequestDto<Object> requestDto) {
        Pageable pageable = PageRequest.of(requestDto.getPageNumber(), requestDto.getRowsNumber());
        return contactTypeMapper.toResponsePage(
                contactTypeRepository.findAll(
                        pageable
                )
        );
    }

    @Override
    public ContactTypeResponse readById(Long contactTypeId) {
        return contactTypeRepository.findById(contactTypeId)
                .map(contactTypeMapper::toResponse)
                .orElseThrow(
                        () -> new IllegalArgumentException("No se ha encontrado un tipo de contacto con id ".concat(contactTypeId.toString()))
                );
    }

    @Override
    public ContactTypeResponse modify(Long contactTypeId, ContactTypeRequest contactTypeRequest) {
        return contactTypeRepository.findById(contactTypeId)
                .map(contactType -> {
                    ContactType updatedContactType = contactTypeMapper.toEntity(contactTypeRequest);
                    updatedContactType.setId(contactType.getId());

                    contactTypeRepository.save(updatedContactType);
                    return updatedContactType;
                })
                .map(contactTypeMapper::toResponse)
                .orElseThrow(
                        () -> new IllegalArgumentException("No se ha encontrado un tipo de contacto con id ".concat(contactTypeId.toString()))
                );
    }

    @Override
    public void remove(Long contactTypeId) {
        contactTypeRepository.findById(contactTypeId)
                .orElseThrow(
                        () -> new IllegalArgumentException("No se ha encontrado un tipo de contacto con id ".concat(contactTypeId.toString()))
                );

        contactTypeRepository.deleteById(contactTypeId);
    }

    @Override
    public Page<ContactTypeResponse> searchNameOrDescription(RequestDto<NameOrDescriptionRequest> contactTypeRequest) {
        Pageable pageable = PageRequest.of(contactTypeRequest.getPageNumber(), contactTypeRequest.getRowsNumber());
        return contactTypeMapper.toResponsePage(
                contactTypeRepository.findByNameContainingIgnoreCaseOrDescriptionContainsIgnoreCase(
                        contactTypeRequest.getData().getName(),
                        contactTypeRequest.getData().getDescription(),
                        pageable
                )
        );
    }
}
