package com.portalClientesPrimadera.repository;

import com.portalClientesPrimadera.model.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.portalClientesPrimadera.model.AvailabilityEntity;

import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository <AvailabilityEntity, Long> {

    @Query(value = "SELECT a FROM AvailabilityEntity a WHERE a.inventory_item_id = :inventory_item_id")
    public List<AvailabilityEntity> findByinventoryitemid(@Param("inventory_item_id") Long inventory_item_id);
    
}
