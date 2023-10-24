package com.portalClientesPrimadera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.portalClientesPrimadera.model.IVAEntity;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface IVARepository extends JpaRepository <IVAEntity, Long>{

    @Query("SELECT i FROM IVAEntity i WHERE :currentDate BETWEEN i.CP_IVA_date_start AND i.CP_IVA_date_end")
    Optional<IVAEntity> findActiveIVA(@Param("currentDate") Date currentDate);
    
}
