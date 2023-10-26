package com.portalClientesPrimadera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portalClientesPrimadera.model.NotificationsEntity;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface NotificationsRepository extends JpaRepository<NotificationsEntity, Long>{
    @Query(value = "SELECT n FROM NotificationsEntity n WHERE n.CP_Notification_context = :CP_Notification_context ")
    public List<NotificationsEntity> finbyCPNotificationcontext(@Param("CP_Notification_context") String CP_Notification_context);
    
}
