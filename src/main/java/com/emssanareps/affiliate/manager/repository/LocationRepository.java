package com.emssanareps.affiliate.manager.repository;

import com.emssanareps.affiliate.manager.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
