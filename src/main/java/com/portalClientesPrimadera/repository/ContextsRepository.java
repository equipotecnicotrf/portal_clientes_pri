package com.portalClientesPrimadera.repository;

import com.portalClientesPrimadera.model.AuditEntity;
import com.portalClientesPrimadera.model.ContextsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContextsRepository extends JpaRepository<ContextsEntity, Long> {

    @Query("select ce from ContextsEntity ce where ce.CP_context_id = ?1")
    Optional<ContextsEntity> getCP_context_id(Long CP_context_id);

}
