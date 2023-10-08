package com.portalClientesPrimadera.repository;

import com.portalClientesPrimadera.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long>{
    UsersEntity findByUsername(String username);

}
