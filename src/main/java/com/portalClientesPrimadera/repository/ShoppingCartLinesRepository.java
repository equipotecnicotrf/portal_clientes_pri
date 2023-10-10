package com.portalClientesPrimadera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portalClientesPrimadera.model.ShoppingCartLinesEntity;

@Repository
public interface ShoppingCartLinesRepository extends JpaRepository <ShoppingCartLinesEntity, Long> {
    
}
