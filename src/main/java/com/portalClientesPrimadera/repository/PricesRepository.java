package com.portalClientesPrimadera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portalClientesPrimadera.model.PricesEntity;

@Repository
public interface PricesRepository extends JpaRepository <PricesEntity, Long>{
    
}
