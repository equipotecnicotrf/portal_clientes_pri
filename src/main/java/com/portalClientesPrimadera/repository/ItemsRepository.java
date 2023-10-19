package com.portalClientesPrimadera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.portalClientesPrimadera.model.ItemsEntity;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository <ItemsEntity,Long>{
    @Query(value = "SELECT i FROM ItemsEntity i, AvailabilityEntity a WHERE i.inventory_item_id = a.inventory_item_id and a.Available_to_transact <> 0"
    )
    public List<ItemsEntity> findByDisponibilidad();

}
