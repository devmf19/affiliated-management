package com.emssanareps.affiliate.manager.repository;

import com.emssanareps.affiliate.manager.enums.ContactType;
import com.emssanareps.affiliate.manager.model.AffiliateContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AffiliateContactRepository extends JpaRepository<AffiliateContact, Long> {
    Optional<AffiliateContact> findByAffiliateIdAndContactType(Long affiliateId, ContactType contactType);
}
