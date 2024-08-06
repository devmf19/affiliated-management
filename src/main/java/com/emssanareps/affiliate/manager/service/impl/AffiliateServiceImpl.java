package com.emssanareps.affiliate.manager.service.impl;

import com.emssanareps.affiliate.manager.dto.request.AffiliateRequest;
import com.emssanareps.affiliate.manager.dto.response.AffiliateResponse;
import com.emssanareps.affiliate.manager.dto.response.BeneficiaryResponse;
import com.emssanareps.affiliate.manager.enums.CivilStatus;
import com.emssanareps.affiliate.manager.enums.Genre;
import com.emssanareps.affiliate.manager.enums.Regime;
import com.emssanareps.affiliate.manager.mapper.AffiliateMapper;
import com.emssanareps.affiliate.manager.model.Affiliate;
import com.emssanareps.affiliate.manager.model.DocumentType;
import com.emssanareps.affiliate.manager.model.Location;
import com.emssanareps.affiliate.manager.repository.AffiliateRepository;
import com.emssanareps.affiliate.manager.repository.DocumentTypeRepository;
import com.emssanareps.affiliate.manager.service.AffiliateContactService;
import com.emssanareps.affiliate.manager.service.AffiliateService;
import com.emssanareps.affiliate.manager.service.BeneficiaryService;
import com.emssanareps.affiliate.manager.service.LocationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AffiliateServiceImpl implements AffiliateService {

    private final AffiliateRepository affiliateRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final BeneficiaryService beneficiaryService;
    private final LocationService locationService;
    private final AffiliateContactService affiliateContactService;
    private final AffiliateMapper affiliateMapper;

    @Autowired
    public AffiliateServiceImpl(AffiliateRepository affiliateRepository, DocumentTypeRepository documentTypeRepository, BeneficiaryService beneficiaryService, LocationService locationService, AffiliateContactService affiliateContactService, AffiliateMapper affiliateMapper) {
        this.affiliateRepository = affiliateRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.beneficiaryService = beneficiaryService;
        this.locationService = locationService;
        this.affiliateContactService = affiliateContactService;
        this.affiliateMapper = affiliateMapper;
    }

    @Override
    @Transactional
    public AffiliateResponse create(AffiliateRequest affiliateRequest) {
        DocumentType documentType = documentTypeRepository.findById(affiliateRequest.getDocumentTypeId())
                .orElseThrow(
                        () -> new IllegalArgumentException("No se econtro el tipo de documento")
                );

        if (affiliateRepository.existsByIdentificationNumber(affiliateRequest.getIdentificationNumber()))
            throw new IllegalArgumentException("el numero de identidad ".concat(affiliateRequest.getIdentificationNumber().toString()).concat(" ya se encuentra registrado"));


        Location location = locationService.create(affiliateRequest.getLocation());

        Affiliate toSave = affiliateMapper.toEntity(affiliateRequest);

        toSave.setDocumentType(documentType);
        toSave.setLocation(location);
        toSave.setGenre(Genre.fromValue(affiliateRequest.getGenre()));
        toSave.setRegime(Regime.fromValue(affiliateRequest.getRegime()));
        toSave.setCivilStatus(CivilStatus.fromValue(affiliateRequest.getCivilStatus()));

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
    public List<AffiliateResponse> readAll() {
        return affiliateMapper.toResponseList(affiliateRepository.findAll());
    }

    @Override
    public AffiliateResponse readById(Long affiliateId) {
        return affiliateRepository.findById(affiliateId)
                .map(affiliateMapper::toResponse)
                .orElseThrow(
                        () -> new IllegalArgumentException("No se ha encontrado un afilido con id ".concat(affiliateId.toString()))
                );
    }
}
