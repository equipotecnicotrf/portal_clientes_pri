package com.portalClientesPrimadera.repository;

import com.portalClientesPrimadera.model.AuditEntity;
import com.portalClientesPrimadera.model.ContextsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContextsRepository extends JpaRepository<ContextsEntity, Long> {

}
