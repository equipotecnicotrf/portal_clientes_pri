package com.portalClientesPrimadera.repository;

import com.portalClientesPrimadera.model.PortalStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortalStatusRepository extends JpaRepository<PortalStatusEntity, Long> {

}
