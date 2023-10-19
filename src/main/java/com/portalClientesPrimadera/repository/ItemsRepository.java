package com.portalClientesPrimadera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.portalClientesPrimadera.model.ItemsEntity;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository <ItemsEntity,Long>{
    @Query(value = "SELECT i FROM ItemsEntity i, AvailabilityEntity a WHERE i.inventory_item_id = a.inventory_item_id AND a.Available_to_transact <> 0 AND i.Atribute9 <> 0"
    )
    public List<ItemsEntity> findByDisponibilidad();

    @Query(value = "SELECT i FROM ItemsEntity i WHERE  i.Atribute9 <> 0")
    public List<ItemsEntity> FinbyHazPedido();

}
