package com.emssanareps.affiliate.manager.service.impl;

import com.emssanareps.affiliate.manager.dto.request.BeneficiaryTypeRequest;
import com.emssanareps.affiliate.manager.dto.response.BeneficiaryTypeResponse;
import com.emssanareps.affiliate.manager.mapper.BeneficiaryTypeMapper;
import com.emssanareps.affiliate.manager.repository.BeneficiaryTypeRepository;
import com.emssanareps.affiliate.manager.service.BeneficiaryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<BeneficiaryTypeResponse> readAll() {
        return beneficiaryTypeMapper.toResponseList(
                beneficiaryTypeRepository.findAll()
        );
    }

    @Override
    public BeneficiaryTypeResponse readById(Long beneficiaryTypeId) {
        return beneficiaryTypeRepository.findById(beneficiaryTypeId)
                .map(beneficiaryTypeMapper::toResponse)
                .orElseThrow(
                        () -> new IllegalArgumentException("No se ha encontrado un tipo de beneficiario con id ".concat(beneficiaryTypeId.toString()))
                );
    }
}
