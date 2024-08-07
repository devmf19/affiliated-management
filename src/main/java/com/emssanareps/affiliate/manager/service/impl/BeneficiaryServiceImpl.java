package com.emssanareps.affiliate.manager.service.impl;

import com.emssanareps.affiliate.manager.dto.request.BeneficiaryRequest;
import com.emssanareps.affiliate.manager.dto.request.NameOrLastnameRequest;
import com.emssanareps.affiliate.manager.dto.request.RequestDto;
import com.emssanareps.affiliate.manager.dto.response.BeneficiaryResponse;
import com.emssanareps.affiliate.manager.enums.BeneficiaryType;
import com.emssanareps.affiliate.manager.enums.DocumentType;
import com.emssanareps.affiliate.manager.enums.Genre;
import com.emssanareps.affiliate.manager.enums.Status;
import com.emssanareps.affiliate.manager.mapper.BeneficiaryMapper;
import com.emssanareps.affiliate.manager.model.Beneficiary;
import com.emssanareps.affiliate.manager.model.Location;
import com.emssanareps.affiliate.manager.repository.BeneficiaryRepository;
import com.emssanareps.affiliate.manager.service.BeneficiaryService;
import com.emssanareps.affiliate.manager.service.LocationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {
    private final BeneficiaryRepository beneficiaryRepository;
    private final LocationService locationService;
    private final BeneficiaryMapper beneficiaryMapper;

    @Autowired
    public BeneficiaryServiceImpl(BeneficiaryRepository beneficiaryRepository, LocationService locationService, BeneficiaryMapper beneficiaryMapper) {
        this.beneficiaryRepository = beneficiaryRepository;
        this.locationService = locationService;
        this.beneficiaryMapper = beneficiaryMapper;
    }

    @Override
    @Transactional
    public BeneficiaryResponse create(Long affiliateId, BeneficiaryRequest beneficiaryRequest) {
        if (beneficiaryRepository.existsByIdentificationNumber(beneficiaryRequest.getIdentificationNumber()))
            throw new IllegalArgumentException("Ha ocurrido un error con el beneficiario ".concat(beneficiaryRequest.getName()));


        Location location = locationService.create(beneficiaryRequest.getLocation());

        Beneficiary toSave = beneficiaryMapper.toEntity(beneficiaryRequest);

        toSave.setDocumentType(DocumentType.fromValue(beneficiaryRequest.getBeneficiaryType()));
        toSave.setBeneficiaryType(BeneficiaryType.fromValue(beneficiaryRequest.getBeneficiaryType()));
        toSave.setLocation(location);
        toSave.setGenre(Genre.fromValue(beneficiaryRequest.getGenre()));
        toSave.setStatus(Status.ACTIVO);
        toSave.setAffiliateId(affiliateId);

        return beneficiaryMapper.toResponse(beneficiaryRepository.save(toSave));
    }

    @Override
    public Page<BeneficiaryResponse> readAll(RequestDto<Object> requestDto) {
        Pageable pageable = PageRequest.of(requestDto.getPageNumber(), requestDto.getRowsNumber());
        return beneficiaryMapper.toResponsePage(beneficiaryRepository.findAll(pageable));
    }

    @Override
    public BeneficiaryResponse readById(Long beneficiaryId) {
        return beneficiaryRepository.findById(beneficiaryId)
                .map(beneficiaryMapper::toResponse)
                .orElseThrow(
                        () -> new IllegalArgumentException("No se ha encontrado un beneficiario con id ".concat(beneficiaryId.toString()))
                );
    }

    @Override
    @Transactional
    public BeneficiaryResponse modify(Long beneficiaryId, BeneficiaryRequest beneficiaryRequest) {
        Beneficiary old = beneficiaryRepository.findById(beneficiaryId)
                .orElseThrow(
                        () -> new IllegalArgumentException("No se ha encontrado un beneficiario con id ".concat(beneficiaryId.toString()))
                );

        Beneficiary toUpdate = beneficiaryMapper.toEntity(beneficiaryRequest);

        Location location = locationService.modify(old.getLocation().getId(), beneficiaryRequest.getLocation());

        toUpdate.setDocumentType(DocumentType.fromValue(beneficiaryRequest.getDocumentType()));
        toUpdate.setBeneficiaryType(BeneficiaryType.fromValue(beneficiaryRequest.getBeneficiaryType()));
        toUpdate.setLocation(location);
        toUpdate.setGenre(Genre.fromValue(beneficiaryRequest.getGenre()));
        toUpdate.setStatus(Status.fromValue(beneficiaryRequest.getStatus()));

        return beneficiaryMapper.toResponse(
                beneficiaryRepository.save(toUpdate)
        );
    }

    @Override
    public void remove(Long beneficiaryId) {
        beneficiaryRepository.delete(
                beneficiaryRepository.findById(beneficiaryId)
                        .orElseThrow(
                                () -> new IllegalArgumentException("No se ha encontrado un beneficiario con id ".concat(beneficiaryId.toString()))
                        )
        );
    }

    @Override
    public Page<BeneficiaryResponse> searchNameOrDescription(RequestDto<NameOrLastnameRequest> beneficiaryRequest) {
        Pageable pageable = PageRequest.of(beneficiaryRequest.getPageNumber(), beneficiaryRequest.getRowsNumber());
        return beneficiaryMapper.toResponsePage(
                beneficiaryRepository.findByNameContainingIgnoreCaseOrLastnameContainingIgnoreCase(
                        beneficiaryRequest.getData().getName(),
                        beneficiaryRequest.getData().getLastname(),
                        pageable
                )
        );
    }
}
