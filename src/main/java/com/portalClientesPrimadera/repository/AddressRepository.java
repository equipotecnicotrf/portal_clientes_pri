package com.portalClientesPrimadera.repository;

import com.portalClientesPrimadera.model.AddressesEntity;
import com.portalClientesPrimadera.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository <AddressesEntity, Long> {
    @Query("select a from AddressesEntity a where a.site_use_id = ?1")
    Optional<AddressesEntity> getsite_use_id(Integer site_use_id);
}
