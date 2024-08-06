package com.emssanareps.affiliate.manager.repository;

import com.emssanareps.affiliate.manager.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
    boolean existsByIdentificationNumber(Long identificationNumber);
}
