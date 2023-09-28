package com.portalClientesPrimadera.repository;

import com.portalClientesPrimadera.model.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository <RolesEntity, Long> {

    @Query("select r from RolesEntity r where r.CP_rol_id = ?1")
    Optional<RolesEntity> getRolByCP_rol_id(Long CP_rol_id);

}
