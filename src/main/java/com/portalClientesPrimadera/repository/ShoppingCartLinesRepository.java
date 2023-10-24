package com.portalClientesPrimadera.repository;

import com.portalClientesPrimadera.model.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portalClientesPrimadera.model.ShoppingCartLinesEntity;

import java.util.List;

@Repository
public interface ShoppingCartLinesRepository extends JpaRepository <ShoppingCartLinesEntity, Long> {
    @Query(value = "SELECT s FROM ShoppingCartLinesEntity s WHERE s.CP_cart_id = :cp_cart_id")
    public List<ShoppingCartLinesEntity> findBycpcartid(@Param("cp_cart_id") Long cp_cart_id);
}
