package com.emssanareps.affiliate.manager.service.impl;

import com.emssanareps.affiliate.manager.constants.AffiliateConstants;
import com.emssanareps.affiliate.manager.dto.request.AffiliateRequest;
import com.emssanareps.affiliate.manager.dto.request.NameOrLastnameRequest;
import com.emssanareps.affiliate.manager.dto.request.RequestDto;
import com.emssanareps.affiliate.manager.dto.response.AffiliateResponse;
import com.emssanareps.affiliate.manager.enums.*;
import com.emssanareps.affiliate.manager.mapper.AffiliateMapper;
import com.emssanareps.affiliate.manager.model.Affiliate;
import com.emssanareps.affiliate.manager.model.Location;
import com.emssanareps.affiliate.manager.repository.AffiliateRepository;
import com.emssanareps.affiliate.manager.service.AffiliateContactService;
import com.emssanareps.affiliate.manager.service.AffiliateService;
import com.emssanareps.affiliate.manager.service.BeneficiaryService;
import com.emssanareps.affiliate.manager.service.LocationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AffiliateServiceImpl implements AffiliateService {

    private final AffiliateRepository affiliateRepository;
    private final BeneficiaryService beneficiaryService;
    private final LocationService locationService;
    private final AffiliateContactService affiliateContactService;
    private final AffiliateMapper affiliateMapper;

    @Autowired
    public AffiliateServiceImpl(AffiliateRepository affiliateRepository, BeneficiaryService beneficiaryService, LocationService locationService, AffiliateContactService affiliateContactService, AffiliateMapper affiliateMapper) {
        this.affiliateRepository = affiliateRepository;
        this.beneficiaryService = beneficiaryService;
        this.locationService = locationService;
        this.affiliateContactService = affiliateContactService;
        this.affiliateMapper = affiliateMapper;
    }

    @Override
    @Transactional
    public AffiliateResponse create(AffiliateRequest affiliateRequest) {

        if (affiliateRepository.existsByIdentificationNumber(affiliateRequest.getIdentificationNumber()))
            throw new IllegalArgumentException(AffiliateConstants.REPEATED_IDENTIFICATION_NUMBER.concat(affiliateRequest.getIdentificationNumber().toString()));


        Location location = locationService.create(affiliateRequest.getLocation());

        Affiliate toSave = affiliateMapper.toEntity(affiliateRequest);

        toSave.setLocation(location);
        toSave.setDocumentType(DocumentType.fromValue(affiliateRequest.getDocumentType()));
        toSave.setGenre(Genre.fromValue(affiliateRequest.getGenre()));
        toSave.setRegime(Regime.fromValue(affiliateRequest.getRegime()));
        toSave.setCivilStatus(CivilStatus.fromValue(affiliateRequest.getCivilStatus()));
        toSave.setStatus(Status.ACTIVO);

        AffiliateResponse saved = affiliateMapper.toResponse(affiliateRepository.save(toSave));

        affiliateRequest.getBeneficiaries().forEach(beneficiary -> {
            saved.getBeneficiaries().add(beneficiaryService.create(saved.getId(), beneficiary));
        });

        affiliateRequest.getContacts().forEach(contact -> {
            saved.getContacts().add(
                    affiliateContactService.create(saved.getId(), contact)
            );
        });

        return saved;
    }

    @Override
    public Page<AffiliateResponse> readAll(RequestDto<Object> requestDto) {
        Pageable pageable = PageRequest.of(requestDto.getPageNumber(), requestDto.getRowsNumber());
        return affiliateMapper.toResponsePage(
                affiliateRepository.findAll(pageable)
        );
    }

    @Override
    public AffiliateResponse readById(Long affiliateId) {
        return affiliateRepository.findById(affiliateId)
                .map(affiliateMapper::toResponse)
                .orElseThrow(
                        () -> new EntityNotFoundException(AffiliateConstants.NOT_FOUND.concat(affiliateId.toString()))
                );
    }

    @Override
    public AffiliateResponse modify(Long affiliateId, AffiliateRequest affiliateRequest) {
        Affiliate old = affiliateRepository.findById(affiliateId)
                .orElseThrow(
                        () -> new EntityNotFoundException(AffiliateConstants.NOT_FOUND.concat(affiliateId.toString()))
                );

        Affiliate toUpdate = affiliateMapper.toEntity(affiliateRequest);

        Location location = locationService.modify(old.getLocation().getId(), affiliateRequest.getLocation());

        toUpdate.setDocumentType(DocumentType.fromValue(affiliateRequest.getDocumentType()));
        toUpdate.setLocation(location);
        toUpdate.setGenre(Genre.fromValue(affiliateRequest.getGenre()));
        toUpdate.setStatus(Status.fromValue(affiliateRequest.getStatus()));
        toUpdate.setBeneficiaries(old.getBeneficiaries());
        toUpdate.setContacts(old.getContacts());

        return affiliateMapper.toResponse(
                affiliateRepository.save(toUpdate)
        );
    }

    @Override
    public void remove(Long affiliateId) {
        affiliateRepository.delete(
                affiliateRepository.findById(affiliateId)
                        .orElseThrow(
                                () -> new EntityNotFoundException(AffiliateConstants.NOT_FOUND.concat(affiliateId.toString()))
                        )
        );
    }

    @Override
    public Page<AffiliateResponse> searchNameOrDescription(RequestDto<NameOrLastnameRequest> affiliateRequest) {
        Pageable pageable = PageRequest.of(affiliateRequest.getPageNumber(), affiliateRequest.getRowsNumber());
        return affiliateMapper.toResponsePage(
                affiliateRepository.findByNameContainingIgnoreCaseOrLastnameContainingIgnoreCase(
                        affiliateRequest.getData().getName(),
                        affiliateRequest.getData().getLastname(),
                        pageable
                )
        );
    }
}
