package com.portalClientesPrimadera.repository;

import com.portalClientesPrimadera.model.AddressesEntity;
import com.portalClientesPrimadera.model.TypeOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeOrderRepository extends JpaRepository <TypeOrderEntity, Long> {

    @Query("select t from TypeOrderEntity t where t.CP_type_order_id = ?1")
    Optional<TypeOrderEntity> getCP_type_order_id(Integer CP_type_order_id);
}
