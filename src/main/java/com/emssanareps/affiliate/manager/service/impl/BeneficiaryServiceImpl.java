package com.emssanareps.affiliate.manager.service.impl;

import com.emssanareps.affiliate.manager.dto.request.BeneficiaryRequest;
import com.emssanareps.affiliate.manager.dto.response.BeneficiaryResponse;
import com.emssanareps.affiliate.manager.enums.Genre;
import com.emssanareps.affiliate.manager.enums.Status;
import com.emssanareps.affiliate.manager.mapper.BeneficiaryMapper;
import com.emssanareps.affiliate.manager.model.Beneficiary;
import com.emssanareps.affiliate.manager.model.BeneficiaryType;
import com.emssanareps.affiliate.manager.model.DocumentType;
import com.emssanareps.affiliate.manager.model.Location;
import com.emssanareps.affiliate.manager.repository.AffiliateRepository;
import com.emssanareps.affiliate.manager.repository.BeneficiaryRepository;
import com.emssanareps.affiliate.manager.repository.BeneficiaryTypeRepository;
import com.emssanareps.affiliate.manager.repository.DocumentTypeRepository;
import com.emssanareps.affiliate.manager.service.BeneficiaryService;
import com.emssanareps.affiliate.manager.service.LocationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {
    private final BeneficiaryRepository beneficiaryRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final BeneficiaryTypeRepository beneficiaryTypeRepository;
    private final LocationService locationService;
    private final BeneficiaryMapper beneficiaryMapper;

    @Autowired
    public BeneficiaryServiceImpl(BeneficiaryRepository beneficiaryRepository, DocumentTypeRepository documentTypeRepository, BeneficiaryTypeRepository beneficiaryTypeRepository, LocationService locationService, BeneficiaryMapper beneficiaryMapper) {
        this.beneficiaryRepository = beneficiaryRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.beneficiaryTypeRepository = beneficiaryTypeRepository;
        this.locationService = locationService;
        this.beneficiaryMapper = beneficiaryMapper;
    }

    @Override
    @Transactional
    public BeneficiaryResponse create(Long affiliateId, BeneficiaryRequest beneficiaryRequest) {
        DocumentType documentType = documentTypeRepository.findById(beneficiaryRequest.getDocumentTypeId())
                .orElseThrow(
                        () -> new IllegalArgumentException("No se econtro el tipo de documento")
                );

        BeneficiaryType beneficiaryType = beneficiaryTypeRepository.findById(beneficiaryRequest.getBeneficiaryTypeId())
                .orElseThrow(
                        () -> new IllegalArgumentException("No se econtro el tipo de beneficiario")
                );

        if (!beneficiaryRepository.existsByIdentificationNumber(beneficiaryRequest.getIdentificationNumber())) {
            Location location = locationService.create(beneficiaryRequest.getLocation());

            Beneficiary toSave = beneficiaryMapper.toEntity(beneficiaryRequest);

            toSave.setDocumentType(documentType);
            toSave.setBeneficiaryType(beneficiaryType);
            toSave.setLocation(location);
            toSave.setGenre(Genre.fromValue(beneficiaryRequest.getGenre()));
            toSave.setStatus(Status.ACTIVO);
            toSave.setAffiliateId(affiliateId);

            return beneficiaryMapper.toResponse(beneficiaryRepository.save(toSave));

        }
        throw new IllegalArgumentException("Ha ocurrido un error con el beneficiario ".concat(beneficiaryRequest.getName()));
    }
}
