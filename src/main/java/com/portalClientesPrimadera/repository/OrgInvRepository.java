package com.portalClientesPrimadera.repository;

import com.portalClientesPrimadera.model.OrgInvEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgInvRepository extends JpaRepository<OrgInvEntity, Long> {

}
