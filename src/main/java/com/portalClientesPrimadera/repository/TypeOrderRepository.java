package com.portalClientesPrimadera.repository;

import com.portalClientesPrimadera.model.TypeOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOrderRepository extends JpaRepository<TypeOrderEntity, Long> {

}
