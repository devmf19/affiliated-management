package com.emssanareps.affiliate.manager.repository;

import com.emssanareps.affiliate.manager.model.BeneficiaryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaryTypeRepository extends JpaRepository<BeneficiaryType, Long> {
    @Override
    Page<BeneficiaryType> findAll(Pageable pageable);

    Page<BeneficiaryType> findByNameContainingIgnoreCaseOrDescriptionContainsIgnoreCase(String name, String description, Pageable pageable);
}
