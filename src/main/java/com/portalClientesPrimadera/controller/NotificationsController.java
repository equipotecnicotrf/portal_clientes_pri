package com.portalClientesPrimadera.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import com.portalClientesPrimadera.model.AddressesEntity;
import com.portalClientesPrimadera.model.NotificationsEntity;
import com.portalClientesPrimadera.repository.AddressRepository;
import com.portalClientesPrimadera.repository.NotificationsRepository;

@RestController
@RequestMapping("/api/v1")
public class NotificationsController {

    @Autowired
    NotificationsRepository notificationsRepository;

    @GetMapping("/Notifications")
    public List<NotificationsEntity> ListarNotifications() {
        return notificationsRepository.findAll();
    }

    @PostMapping("/Notifications")
    public NotificationsEntity saveNotifications(@RequestBody NotificationsEntity Notification) {
        return notificationsRepository.save(Notification);
    }

    @GetMapping("/Notifications/{id}")
    public ResponseEntity<NotificationsEntity> getNotificationsBySiteId(@PathVariable Long id) {
        NotificationsEntity notification = notificationsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La notificacion por este id no existe" + id));
        return ResponseEntity.ok(notification);
    }
    
}
