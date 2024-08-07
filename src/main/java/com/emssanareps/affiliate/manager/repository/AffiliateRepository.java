package com.emssanareps.affiliate.manager.repository;

import com.emssanareps.affiliate.manager.model.Affiliate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AffiliateRepository extends JpaRepository<Affiliate, Long> {
    @Override
    Page<Affiliate> findAll(Pageable pageable);

    boolean existsByIdentificationNumber(Long identificationNumber);

    Page<Affiliate> findByNameContainingIgnoreCaseOrLastnameContainingIgnoreCase(String name, String lastname, Pageable pageable);
}
