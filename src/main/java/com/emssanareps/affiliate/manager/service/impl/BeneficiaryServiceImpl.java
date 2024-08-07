package com.emssanareps.affiliate.manager.service.impl;

import com.emssanareps.affiliate.manager.constants.BeneficiaryConstants;
import com.emssanareps.affiliate.manager.dto.request.BeneficiaryRequest;
import com.emssanareps.affiliate.manager.dto.request.NameOrLastnameRequest;
import com.emssanareps.affiliate.manager.dto.request.RequestDto;
import com.emssanareps.affiliate.manager.dto.response.AffiliateResponse;
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
import com.emssanareps.affiliate.manager.service.DaneService;
import com.emssanareps.affiliate.manager.service.LocationService;
import com.emssanareps.affiliate.manager.util.DaneLocation;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {
    private final BeneficiaryRepository beneficiaryRepository;
    private final LocationService locationService;
    private final DaneService daneService;
    private final BeneficiaryMapper beneficiaryMapper;

    @Autowired
    public BeneficiaryServiceImpl(BeneficiaryRepository beneficiaryRepository, LocationService locationService, DaneService daneService, BeneficiaryMapper beneficiaryMapper) {
        this.beneficiaryRepository = beneficiaryRepository;
        this.locationService = locationService;
        this.daneService = daneService;
        this.beneficiaryMapper = beneficiaryMapper;
    }

    @Override
    @Transactional
    public BeneficiaryResponse create(Long affiliateId, BeneficiaryRequest beneficiaryRequest) {
        if (beneficiaryRepository.existsByIdentificationNumber(beneficiaryRequest.getIdentificationNumber()))
            throw new IllegalArgumentException(BeneficiaryConstants.REPEATED_IDENTIFICATION_NUMBER.concat(beneficiaryRequest.getName()));

        ageValidation(beneficiaryRequest);

        Location location = locationService.create(beneficiaryRequest.getLocation());
        Beneficiary toSave = beneficiaryMapper.toEntity(beneficiaryRequest);

        toSave.setDocumentType(DocumentType.fromValue(beneficiaryRequest.getDocumentType()));
        toSave.setBeneficiaryType(BeneficiaryType.fromValue(beneficiaryRequest.getBeneficiaryType()));
        toSave.setLocation(location);
        toSave.setGenre(Genre.fromValue(beneficiaryRequest.getGenre()));
        toSave.setStatus(Status.ACTIVO);
        toSave.setAffiliateId(affiliateId);

        return mapLocation(beneficiaryRepository.save(toSave));
    }

    @Override
    public Page<BeneficiaryResponse> readAll(RequestDto<Object> requestDto) {
        Pageable pageable = PageRequest.of(requestDto.getPageNumber(), requestDto.getRowsNumber());
        return beneficiaryRepository.findAll(pageable)
                .map(this::mapLocation);
    }

    @Override
    public BeneficiaryResponse readById(Long beneficiaryId) {
        return beneficiaryRepository.findById(beneficiaryId)
                .map(this::mapLocation)
                .orElseThrow(
                        () -> new EntityNotFoundException(BeneficiaryConstants.NOT_FOUND.concat(beneficiaryId.toString()))
                );
    }

    @Override
    @Transactional
    public BeneficiaryResponse modify(Long beneficiaryId, BeneficiaryRequest beneficiaryRequest) {
        Beneficiary old = beneficiaryRepository.findById(beneficiaryId)
                .orElseThrow(
                        () -> new EntityNotFoundException(BeneficiaryConstants.NOT_FOUND.concat(beneficiaryId.toString()))
                );

        ageValidation(beneficiaryRequest);

        Beneficiary toUpdate = beneficiaryMapper.toEntity(beneficiaryRequest);
        Location location = locationService.modify(old.getLocation().getId(), beneficiaryRequest.getLocation());

        toUpdate.setDocumentType(DocumentType.fromValue(beneficiaryRequest.getDocumentType()));
        toUpdate.setBeneficiaryType(BeneficiaryType.fromValue(beneficiaryRequest.getBeneficiaryType()));
        toUpdate.setLocation(location);
        toUpdate.setGenre(Genre.fromValue(beneficiaryRequest.getGenre()));
        toUpdate.setStatus(Status.fromValue(beneficiaryRequest.getStatus()));

        return mapLocation(beneficiaryRepository.save(toUpdate));
    }

    @Override
    public void remove(Long beneficiaryId) {
        beneficiaryRepository.delete(
                beneficiaryRepository.findById(beneficiaryId)
                        .orElseThrow(
                                () -> new EntityNotFoundException(BeneficiaryConstants.NOT_FOUND.concat(beneficiaryId.toString()))
                        )
        );
    }

    @Override
    public Page<BeneficiaryResponse> searchNameOrDescription(RequestDto<NameOrLastnameRequest> beneficiaryRequest) {
        Pageable pageable = PageRequest.of(beneficiaryRequest.getPageNumber(), beneficiaryRequest.getRowsNumber());
        return beneficiaryRepository.findByNameContainingIgnoreCaseOrLastnameContainingIgnoreCase(
                beneficiaryRequest.getData().getName(),
                beneficiaryRequest.getData().getLastname(),
                pageable
        ).map(this::mapLocation);
    }

    private void ageValidation(BeneficiaryRequest beneficiaryRequest) {
        Optional.of(beneficiaryRequest)
                .filter(request -> BeneficiaryType.HIJX.equals(BeneficiaryType.fromValue(request.getBeneficiaryType())))
                .map(request -> Period.between(request.getBirthdate(), LocalDate.now()).getYears())
                .filter(age -> age >= 18)
                .ifPresent(age -> {
                    throw new IllegalArgumentException(BeneficiaryConstants.AGE_ERROR);
                });
    }

    private BeneficiaryResponse mapLocation(Beneficiary beneficiary) {
        DaneLocation daneLocation = daneService.findLocationByCodes(beneficiary.getLocation().getDaneDepartmentCode(), beneficiary.getLocation().getDaneMunicipalityCode());
        BeneficiaryResponse beneficiaryResponse = beneficiaryMapper.toResponse(beneficiary);

        beneficiaryResponse.getLocation().setDepartment(daneLocation.getDepartmentName());
        beneficiaryResponse.getLocation().setMunicipality(daneLocation.getMunicipalityName());

        return beneficiaryResponse;
    }
}
