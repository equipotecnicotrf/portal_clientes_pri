package com.portalClientesPrimadera.repository;

import com.portalClientesPrimadera.model.AddressesEntity;
import com.portalClientesPrimadera.model.OrgInvEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrgInvRepository extends JpaRepository <OrgInvEntity, Long> {

    @Query("select o from OrgInvEntity o where o.organization_id = ?1")
    Optional<OrgInvEntity> getorganization_id(Integer organization_id);

}
