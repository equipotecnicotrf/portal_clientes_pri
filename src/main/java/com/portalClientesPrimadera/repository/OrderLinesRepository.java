package com.portalClientesPrimadera.repository;

import com.portalClientesPrimadera.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portalClientesPrimadera.model.OrderLinesEntity;

import java.util.List;

@Repository
public interface OrderLinesRepository extends JpaRepository <OrderLinesEntity, Long> {
    @Query(value = "SELECT ol FROM OrderLinesEntity ol WHERE ol.CP_cart_line_id = :cp_cart_line_id")
    public List<OrderLinesEntity> findByordercpcartlineid(@Param("cp_cart_line_id") Long cp_cart_line_id);
    
}
