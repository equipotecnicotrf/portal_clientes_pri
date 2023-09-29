package com.portalClientesPrimadera.repository;

import com.portalClientesPrimadera.model.AccessEntity;
import com.portalClientesPrimadera.model.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessRepository extends JpaRepository<AccessEntity, Long> {

    @Query("select ac from AccessEntity ac where ac.CP_Access_id = ?1")
    Optional<AccessEntity> getAccessByCP_Access_id(Long CP_Access_id);

}
