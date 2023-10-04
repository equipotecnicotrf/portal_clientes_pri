package com.portalClientesPrimadera.repository;

import com.portalClientesPrimadera.model.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository <RolesEntity, Long> {

}
