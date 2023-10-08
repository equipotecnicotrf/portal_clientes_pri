package com.portalClientesPrimadera.repository;

import com.portalClientesPrimadera.model.ContextsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContextsRepository extends JpaRepository<ContextsEntity, Long> {

}
