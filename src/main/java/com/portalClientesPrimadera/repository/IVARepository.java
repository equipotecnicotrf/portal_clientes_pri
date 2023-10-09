package com.portalClientesPrimadera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.portalClientesPrimadera.model.IVAEntity;

@Repository
public interface IVARepository extends JpaRepository <IVAEntity, Long>{
    
}
