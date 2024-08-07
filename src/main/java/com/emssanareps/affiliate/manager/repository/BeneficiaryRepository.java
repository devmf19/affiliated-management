package com.emssanareps.affiliate.manager.repository;

import com.emssanareps.affiliate.manager.model.Beneficiary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
    @Override
    Page<Beneficiary> findAll(Pageable pageable);

    boolean existsByIdentificationNumber(Long identificationNumber);

    Page<Beneficiary> findByNameContainingIgnoreCaseOrLastnameContainingIgnoreCase(String name, String lastname, Pageable pageable);
}
