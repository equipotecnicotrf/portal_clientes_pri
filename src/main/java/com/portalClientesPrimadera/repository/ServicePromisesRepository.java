package com.portalClientesPrimadera.repository;

import com.portalClientesPrimadera.model.NotificationsEntity;
import com.portalClientesPrimadera.model.ServicePromisesEntity;
import com.portalClientesPrimadera.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServicePromisesRepository extends JpaRepository<ServicePromisesEntity, Long> {


    @Query(value = "SELECT se FROM ServicePromisesEntity se WHERE se.CP_type_promise = :CP_type_promise ")
    public List<ServicePromisesEntity> findByCPtypepromise(@Param("CP_type_promise") String CP_type_promise);


}
