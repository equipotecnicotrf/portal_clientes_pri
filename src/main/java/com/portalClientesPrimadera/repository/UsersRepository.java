package com.portalClientesPrimadera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.portalClientesPrimadera.model.UsersEntity;





@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long>{
    UsersEntity findByUsername(String username);

}
