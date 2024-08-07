package com.emssanareps.affiliate.manager.service.impl;

import com.emssanareps.affiliate.manager.dto.request.AffiliateContactRequest;
import com.emssanareps.affiliate.manager.dto.response.AffiliateContactResponse;
import com.emssanareps.affiliate.manager.enums.ContactType;
import com.emssanareps.affiliate.manager.mapper.AffiliateContactMapper;
import com.emssanareps.affiliate.manager.model.AffiliateContact;
import com.emssanareps.affiliate.manager.repository.AffiliateContactRepository;
import com.emssanareps.affiliate.manager.service.AffiliateContactService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AffiliateContactServiceImpl implements AffiliateContactService {

    private final AffiliateContactRepository affiliateContactRepository;
    private final AffiliateContactMapper affiliateContactMapper;

    @Autowired
    public AffiliateContactServiceImpl(AffiliateContactRepository affiliateContactRepository, AffiliateContactMapper affiliateContactMapper) {
        this.affiliateContactRepository = affiliateContactRepository;
        this.affiliateContactMapper = affiliateContactMapper;
    }

    @Override
    @Transactional
    public AffiliateContactResponse create(Long affiliateId, AffiliateContactRequest affiliateContactRequest) {
        AffiliateContact toSave = affiliateContactMapper.toEntity(affiliateContactRequest);
        toSave.setContactType(ContactType.fromValue(affiliateContactRequest.getContactType()));
        toSave.setAffiliateId(affiliateId);

        return affiliateContactMapper.toResponse(affiliateContactRepository.save(toSave));
    }
}
