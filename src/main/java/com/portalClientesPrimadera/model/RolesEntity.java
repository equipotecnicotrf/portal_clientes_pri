package com.portalClientesPrimadera.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Roles", uniqueConstraints = {@UniqueConstraint(columnNames = "CP_rol_id"), @UniqueConstraint(columnNames = "CP_rol_name"), @UniqueConstraint(columnNames = "CP_rol_description")})
public class RolesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CP_rol_id;

    @Column(name = "CP_rol_name", nullable = false)
    private String CP_rol_name;

    @Column(name = "CP_rol_description", nullable = false)
    private String CP_rol_description;

    @Column(name = "CP_rol_status", nullable = false)
    private String CP_rol_status;

}
