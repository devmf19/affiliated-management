package com.emssanareps.affiliate.manager.service.impl;

import com.emssanareps.affiliate.manager.constants.ContactConstants;
import com.emssanareps.affiliate.manager.dto.request.AffiliateContactRequest;
import com.emssanareps.affiliate.manager.dto.response.AffiliateContactResponse;
import com.emssanareps.affiliate.manager.enums.ContactType;
import com.emssanareps.affiliate.manager.mapper.AffiliateContactMapper;
import com.emssanareps.affiliate.manager.model.AffiliateContact;
import com.emssanareps.affiliate.manager.repository.AffiliateContactRepository;
import com.emssanareps.affiliate.manager.service.AffiliateService;
import com.emssanareps.affiliate.manager.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    private final AffiliateContactRepository affiliateContactRepository;
    private final AffiliateService affiliateService;
    private final AffiliateContactMapper affiliateContactMapper;

    @Autowired
    public ContactServiceImpl(AffiliateContactRepository affiliateContactRepository, AffiliateService affiliateService, AffiliateContactMapper affiliateContactMapper) {
        this.affiliateContactRepository = affiliateContactRepository;
        this.affiliateService = affiliateService;
        this.affiliateContactMapper = affiliateContactMapper;
    }

    @Override
    public AffiliateContactResponse create(Long affiliateId, AffiliateContactRequest affiliateContactRequest) {
        affiliateId = affiliateService.readById(affiliateId).getId();
        AffiliateContact toSave = affiliateContactMapper.toEntity(affiliateContactRequest);
        toSave.setContactType(ContactType.fromValue(affiliateContactRequest.getContactType()));
        toSave.setAffiliateId(affiliateId);

        return affiliateContactMapper.toResponse(affiliateContactRepository.save(toSave));
    }

    @Override
    public AffiliateContactResponse modify(Long affiliateId, AffiliateContactRequest affiliateContactRequest) {
        affiliateId = affiliateService.readById(affiliateId).getId();

        AffiliateContact affiliateContact = affiliateContactRepository.findByAffiliateIdAndContactType(
                affiliateId,
                ContactType.fromValue(affiliateContactRequest.getContactType())
        ).orElseThrow(() -> new IllegalArgumentException(ContactConstants.MODIFY_ERROR));

        affiliateContact.setContact(affiliateContact.getContact());

        return affiliateContactMapper.toResponse(affiliateContactRepository.save(affiliateContact));
    }

    @Override
    public void remove(Long affiliateId, AffiliateContactRequest affiliateContactRequest) {
        affiliateId = affiliateService.readById(affiliateId).getId();

        AffiliateContact affiliateContact = affiliateContactRepository.findByAffiliateIdAndContactType(
                affiliateId,
                ContactType.fromValue(affiliateContactRequest.getContactType())
        ).orElseThrow(() -> new IllegalArgumentException(ContactConstants.DELETE_ERROR));

        affiliateContactRepository.delete(affiliateContact);
    }
}
