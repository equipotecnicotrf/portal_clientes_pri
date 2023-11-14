package com.portalClientesPrimadera.repository;

import com.portalClientesPrimadera.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long>{
    UsersEntity findByUsername(String username);

    @Query("SELECT DISTINCT u.party_id FROM UsersEntity u")
    public List<Long> findDistinctpartyid();

    @Query(value = "SELECT u, r FROM UsersEntity u, RolesEntity r WHERE u.CP_rol_id = r.CP_rol_id")
    public List<ArrayList> FinbyUserYroles();

}
