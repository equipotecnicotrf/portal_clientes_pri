package com.portalClientesPrimadera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portalClientesPrimadera.model.ShoppingCartEntity;

@Repository
public interface ShoppinCartRepository extends JpaRepository <ShoppingCartEntity, Long> {

}
