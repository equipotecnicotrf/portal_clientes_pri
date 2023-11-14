package com.portalClientesPrimadera.repository;

import com.portalClientesPrimadera.model.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AuditRepository extends JpaRepository<AuditEntity, Long> {
    @Query(value = "SELECT a, u FROM AuditEntity a, UsersEntity u WHERE a.CP_id_user = u.CP_user_id")
    public List<ArrayList> Finbyauditanduser();

}
