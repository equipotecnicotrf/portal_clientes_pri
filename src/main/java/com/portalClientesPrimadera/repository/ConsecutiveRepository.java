package com.portalClientesPrimadera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.portalClientesPrimadera.model.ConsecutiveEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsecutiveRepository extends JpaRepository <ConsecutiveEntity, Long> {
    
}
