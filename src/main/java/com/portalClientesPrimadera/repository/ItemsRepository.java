package com.portalClientesPrimadera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portalClientesPrimadera.model.ItemsEntity;

@Repository
public interface ItemsRepository extends JpaRepository <ItemsEntity,Long>{
    
}
