package com.emssanareps.affiliate.manager.repository;

import com.emssanareps.affiliate.manager.model.DocumentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {
    @Override
    Page<DocumentType> findAll(Pageable pageable);

    Page<DocumentType> findByNameContainingIgnoreCaseOrDescriptionContainsIgnoreCase(String name, String description, Pageable pageable);
}
