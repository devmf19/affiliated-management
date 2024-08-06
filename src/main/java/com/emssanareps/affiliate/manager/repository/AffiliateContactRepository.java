package com.emssanareps.affiliate.manager.repository;

import com.emssanareps.affiliate.manager.model.AffiliateContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AffiliateContactRepository extends JpaRepository<AffiliateContact, Long> {
}
