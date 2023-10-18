package com.portalClientesPrimadera.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Portal_status",uniqueConstraints = @UniqueConstraint(columnNames = "CP_portal_id"))
public class PortalStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CP_portal_id;

    @Column(name = "CP_portal_status", nullable = false)
    private String CP_portal_status;

}
