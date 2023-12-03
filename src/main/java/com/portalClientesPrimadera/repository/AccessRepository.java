package com.portalClientesPrimadera.repository;

import com.portalClientesPrimadera.model.AccessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccessRepository extends JpaRepository<AccessEntity, Long> {

    @Query("select ac from AccessEntity ac where ac.CP_Access_id = ?1")
    Optional<AccessEntity> getAccessByCP_Access_id(Long CP_Access_id);

    @Query(value = "SELECT a, c FROM AccessEntity a, ContextsEntity c  WHERE a.CP_context_id = c.CP_context_id and a.CP_rol_id = :cp_rol_id")
    public List<ArrayList> findByAccessandContext(@Param("cp_rol_id") Long CP_rol_id);

}
