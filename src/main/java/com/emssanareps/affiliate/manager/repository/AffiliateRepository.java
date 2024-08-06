package com.emssanareps.affiliate.manager.repository;

import com.emssanareps.affiliate.manager.model.Affiliate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AffiliateRepository extends JpaRepository<Affiliate, Long> {
    boolean existsByIdentificationNumber(Long identificationNumber);
}
