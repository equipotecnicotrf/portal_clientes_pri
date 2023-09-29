package com.portalClientesPrimadera.repository;

import com.portalClientesPrimadera.model.PortalStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortalStatusRepository extends JpaRepository<PortalStatusEntity, Long> {

    @Query("select pr from PortalStatusEntity pr where pr.CP_portal_id = ?1")
    Optional<PortalStatusEntity> getPortalStatusById(Long CP_portal_id);

}
