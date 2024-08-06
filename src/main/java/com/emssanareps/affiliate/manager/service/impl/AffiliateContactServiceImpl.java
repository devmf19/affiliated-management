package com.emssanareps.affiliate.manager.service.impl;

import com.emssanareps.affiliate.manager.dto.request.AffiliateContactRequest;
import com.emssanareps.affiliate.manager.dto.response.AffiliateContactResponse;
import com.emssanareps.affiliate.manager.mapper.AffiliateContactMapper;
import com.emssanareps.affiliate.manager.model.AffiliateContact;
import com.emssanareps.affiliate.manager.model.ContactType;
import com.emssanareps.affiliate.manager.repository.AffiliateContactRepository;
import com.emssanareps.affiliate.manager.repository.ContactTypeRepository;
import com.emssanareps.affiliate.manager.service.AffiliateContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AffiliateContactServiceImpl implements AffiliateContactService {

    private final AffiliateContactRepository affiliateContactRepository;
    private final ContactTypeRepository contactTypeRepository;
    private final AffiliateContactMapper affiliateContactMapper;

    @Autowired
    public AffiliateContactServiceImpl(AffiliateContactRepository affiliateContactRepository, ContactTypeRepository contactTypeRepository, AffiliateContactMapper affiliateContactMapper) {
        this.affiliateContactRepository = affiliateContactRepository;
        this.contactTypeRepository = contactTypeRepository;
        this.affiliateContactMapper = affiliateContactMapper;
    }

    @Override
    public AffiliateContactResponse create(Long affiliateId, AffiliateContactRequest affiliateContactRequest) {
        ContactType contactType = contactTypeRepository.findById(affiliateContactRequest.getContactTypeId()).orElseThrow(
                () -> new IllegalArgumentException("El tipo de contacto es invalido")
        );

        AffiliateContact toSave = affiliateContactMapper.toEntity(affiliateContactRequest);
        toSave.setContactType(contactType);
        toSave.setAffiliateId(affiliateId);

        return affiliateContactMapper.toResponse(affiliateContactRepository.save(toSave));
    }
}
