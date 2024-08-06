package com.emssanareps.affiliate.manager.service.impl;

import com.emssanareps.affiliate.manager.dto.request.ContactTypeRequest;
import com.emssanareps.affiliate.manager.dto.response.ContactTypeResponse;
import com.emssanareps.affiliate.manager.mapper.ContactTypeMapper;
import com.emssanareps.affiliate.manager.repository.ContactTypeRepository;
import com.emssanareps.affiliate.manager.service.ContactTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<ContactTypeResponse> readAll() {
        return contactTypeMapper.toResponseList(
                contactTypeRepository.findAll()
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
}
