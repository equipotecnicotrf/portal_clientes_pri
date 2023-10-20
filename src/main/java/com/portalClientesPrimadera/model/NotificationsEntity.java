package com.portalClientesPrimadera.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Notifications",uniqueConstraints = @UniqueConstraint(columnNames = "CP_Notification_id"))
public class NotificationsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CP_Notification_id;

    @Column(name = "CP_Notifications_context", nullable = false)
    private String CP_Notifications_context;

    @Column(name = "CP_Notifications_name", nullable = false)
    private String CP_Notifications_name;

    @Column(name = "CP_Notifications_message", nullable = false)
    private String CP_Notifications_message;

    
}
