package com.portalClientesPrimadera.repository;

import com.portalClientesPrimadera.model.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuditRepository extends JpaRepository<AuditEntity, Long> {

    @Query("select ae from AuditEntity ae where ae.CP_Audit_id = ?1")
    Optional<AuditEntity> getCP_Audit_id(Long CP_Audit_id);

}
