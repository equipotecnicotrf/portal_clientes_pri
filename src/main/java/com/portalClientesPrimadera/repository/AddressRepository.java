package com.portalClientesPrimadera.repository;

import com.portalClientesPrimadera.model.AddressesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository <AddressesEntity, Long> {

}
