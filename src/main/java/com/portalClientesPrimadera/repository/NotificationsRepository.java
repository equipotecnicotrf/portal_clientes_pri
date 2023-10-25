package com.portalClientesPrimadera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portalClientesPrimadera.model.NotificationsEntity;

@Repository
public interface NotificationsRepository extends JpaRepository<NotificationsEntity, Long>{
    
}