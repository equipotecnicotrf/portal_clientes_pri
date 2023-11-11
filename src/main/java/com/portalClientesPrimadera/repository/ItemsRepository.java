package com.portalClientesPrimadera.repository;

import com.portalClientesPrimadera.model.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portalClientesPrimadera.model.ItemsEntity;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository <ItemsEntity,Long>{
    @Query(value = "SELECT i, a, p FROM ItemsEntity i, AvailabilityEntity a, PricesEntity p WHERE i.inventory_item_id = a.inventory_item_id AND i.inventory_item_id = p.Inventory_item_id AND a.Available_to_transact <> 0 AND i.Atribute9 <> 0 AND p.Cust_account_id = :Cust_account_id")
    public List<ArrayList> findByDisponibilidad(@Param("Cust_account_id") Long Cust_account_id);

    @Query(value = "SELECT i, p FROM ItemsEntity i, PricesEntity p WHERE i.inventory_item_id = p.Inventory_item_id AND i.Atribute9 <> 0 AND p.Cust_account_id = :Cust_account_id AND i.inventory_item_id not in (SELECT a.inventory_item_id FROM AvailabilityEntity a where a.Available_to_transact <> 0)")
    public List<ArrayList> FinbyHazPedido(@Param("Cust_account_id") Long Cust_account_id);

    @Query("SELECT DISTINCT i.Atribute1 FROM ItemsEntity i WHERE i.Atribute1 <> 'N/A' ORDER BY i.Atribute1"  )
    public List<String> findDistinctAttribute1();

    @Query("SELECT DISTINCT i.Atribute2 FROM ItemsEntity i WHERE i.Atribute2 <> 'N/A' ORDER BY i.Atribute2")
    public List<String> findDistinctAttribute2();

    @Query("SELECT DISTINCT i.Atribute3 FROM ItemsEntity i WHERE i.Atribute3 <> '0' ORDER BY i.Atribute3")
    public List<String> findDistinctAttribute3();

    @Query("SELECT DISTINCT i.Atribute4 FROM ItemsEntity i WHERE i.Atribute4 <> 'N/A' ORDER BY i.Atribute4")
    public List<String> findDistinctAttribute4();

    @Query("SELECT DISTINCT i.Atribute5 FROM ItemsEntity i WHERE i.Atribute5 <> 'N/A' ORDER BY i.Atribute5")
    public List<String> findDistinctAttribute5();

    @Query("SELECT DISTINCT i.Atribute6 FROM ItemsEntity i WHERE i.Atribute6 <> '0' ORDER BY i.Atribute6")
    public List<Integer> findDistinctAttribute6();

    @Query("SELECT DISTINCT i.Atribute7 FROM ItemsEntity i WHERE i.Atribute7 <> 'N/A' ORDER BY i.Atribute7")
    public List<String> findDistinctAttribute7();

    @Query(value = "SELECT i FROM ItemsEntity i WHERE i.Atribute9 <> 0")
    public List<ItemsEntity> findByItemsPrices();





}
