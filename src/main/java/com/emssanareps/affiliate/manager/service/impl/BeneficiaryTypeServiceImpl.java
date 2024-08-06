package com.emssanareps.affiliate.manager.service.impl;

import com.emssanareps.affiliate.manager.dto.request.BeneficiaryTypeRequest;
import com.emssanareps.affiliate.manager.dto.request.NameOrDescriptionRequest;
import com.emssanareps.affiliate.manager.dto.request.RequestDto;
import com.emssanareps.affiliate.manager.dto.response.BeneficiaryTypeResponse;
import com.emssanareps.affiliate.manager.mapper.BeneficiaryTypeMapper;
import com.emssanareps.affiliate.manager.model.BeneficiaryType;
import com.emssanareps.affiliate.manager.repository.BeneficiaryTypeRepository;
import com.emssanareps.affiliate.manager.service.BeneficiaryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiaryTypeServiceImpl implements BeneficiaryTypeService {

    private final BeneficiaryTypeRepository beneficiaryTypeRepository;
    private final BeneficiaryTypeMapper beneficiaryTypeMapper;

    @Autowired
    public BeneficiaryTypeServiceImpl(BeneficiaryTypeRepository beneficiaryTypeRepository, BeneficiaryTypeMapper beneficiaryTypeMapper) {
        this.beneficiaryTypeRepository = beneficiaryTypeRepository;
        this.beneficiaryTypeMapper = beneficiaryTypeMapper;
    }

    @Override
    public BeneficiaryTypeResponse create(BeneficiaryTypeRequest beneficiaryTypeRequest) {
        return beneficiaryTypeMapper.toResponse(
                beneficiaryTypeRepository.save(
                        beneficiaryTypeMapper.toEntity(beneficiaryTypeRequest)
                )
        );
    }

    @Override
    public Page<BeneficiaryTypeResponse> readAll(RequestDto<Object> requestDto) {
        Pageable pageable = PageRequest.of(requestDto.getPageNumber(), requestDto.getRowsNumber());
        return beneficiaryTypeMapper.toResponsePage(
                beneficiaryTypeRepository.findAll(
                        pageable
                )
        );
    }

    @Override
    public BeneficiaryTypeResponse readById(Long beneficiaryTypeId) {
        return beneficiaryTypeRepository.findById(beneficiaryTypeId)
                .map(beneficiaryTypeMapper::toResponse)
                .orElseThrow(
                        () -> new IllegalArgumentException("No se ha encontrado un tipo de beneficiaryo con id ".concat(beneficiaryTypeId.toString()))
                );
    }

    @Override
    public BeneficiaryTypeResponse modify(Long beneficiaryTypeId, BeneficiaryTypeRequest beneficiaryTypeRequest) {
        return beneficiaryTypeRepository.findById(beneficiaryTypeId)
                .map(beneficiaryType -> {
                    BeneficiaryType updatedBeneficiaryType = beneficiaryTypeMapper.toEntity(beneficiaryTypeRequest);
                    updatedBeneficiaryType.setId(beneficiaryType.getId());

                    beneficiaryTypeRepository.save(updatedBeneficiaryType);
                    return updatedBeneficiaryType;
                })
                .map(beneficiaryTypeMapper::toResponse)
                .orElseThrow(
                        () -> new IllegalArgumentException("No se ha encontrado un tipo de beneficiaryo con id ".concat(beneficiaryTypeId.toString()))
                );
    }

    @Override
    public void remove(Long beneficiaryTypeId) {
        beneficiaryTypeRepository.findById(beneficiaryTypeId)
                .orElseThrow(
                        () -> new IllegalArgumentException("No se ha encontrado un tipo de beneficiaryo con id ".concat(beneficiaryTypeId.toString()))
                );

        beneficiaryTypeRepository.deleteById(beneficiaryTypeId);
    }

    @Override
    public Page<BeneficiaryTypeResponse> searchNameOrDescription(RequestDto<NameOrDescriptionRequest> beneficiaryTypeRequest) {
        Pageable pageable = PageRequest.of(beneficiaryTypeRequest.getPageNumber(), beneficiaryTypeRequest.getRowsNumber());
        return beneficiaryTypeMapper.toResponsePage(
                beneficiaryTypeRepository.findByNameContainingIgnoreCaseOrDescriptionContainsIgnoreCase(
                        beneficiaryTypeRequest.getData().getName(),
                        beneficiaryTypeRequest.getData().getDescription(),
                        pageable
                )
        );
    }
}
