package com.portalClientesPrimadera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.portalClientesPrimadera.model.ItemsEntity;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository <ItemsEntity,Long>{
    @Query(value = "SELECT i, a FROM ItemsEntity i, AvailabilityEntity a WHERE i.inventory_item_id = a.inventory_item_id AND a.Available_to_transact <> 0 AND i.Atribute9 <> 0")
    public List<ArrayList> findByDisponibilidad();

    @Query(value = "SELECT i FROM ItemsEntity i WHERE  i.Atribute9 <> 0 AND i.inventory_item_id not in (SELECT a.inventory_item_id FROM AvailabilityEntity a where a.Available_to_transact <> 0)")
    public List<ItemsEntity> FinbyHazPedido();

    @Query("SELECT DISTINCT i.Atribute1 FROM ItemsEntity i")
    public List<String> findDistinctAttribute1();

    @Query("SELECT DISTINCT i.Atribute2 FROM ItemsEntity i")
    public List<String> findDistinctAttribute2();

    @Query("SELECT DISTINCT i.Atribute3 FROM ItemsEntity i")
    public List<String> findDistinctAttribute3();

    @Query("SELECT DISTINCT i.Atribute4 FROM ItemsEntity i")
    public List<String> findDistinctAttribute4();

    @Query("SELECT DISTINCT i.Atribute5 FROM ItemsEntity i")
    public List<String> findDistinctAttribute5();

    @Query("SELECT DISTINCT i.Atribute6 FROM ItemsEntity i")
    public List<Integer> findDistinctAttribute6();

    @Query("SELECT DISTINCT i.Atribute7 FROM ItemsEntity i")
    public List<String> findDistinctAttribute7();





}
