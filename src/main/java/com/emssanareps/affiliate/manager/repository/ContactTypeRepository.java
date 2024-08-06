package com.emssanareps.affiliate.manager.repository;

import com.emssanareps.affiliate.manager.model.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactTypeRepository extends JpaRepository<ContactType, Long> {
}
