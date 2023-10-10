package com.portalClientesPrimadera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.portalClientesPrimadera.model.AvailabilityEntity;

@Repository
public interface AvailabilityRepository extends JpaRepository <AvailabilityEntity, Long> {
    
}
