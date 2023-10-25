package com.portalClientesPrimadera.repository;

import com.portalClientesPrimadera.model.ShoppingCartLinesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portalClientesPrimadera.model.OrderEntity;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository <OrderEntity, Long>{
    @Query(value = "SELECT o FROM OrderEntity o WHERE o.CP_cart_id = :cp_cart_id")
    public List<OrderEntity> findByordercpcartid(@Param("cp_cart_id") Long cp_cart_id);
    
}
