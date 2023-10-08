package com.portalClientesPrimadera.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Roles", uniqueConstraints = @UniqueConstraint(columnNames = "CP_rol_id"))
public class RolesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CP_rol_id;

    @Column(name = "CP_rol_name")
    private String CP_rol_name;

    @Column(name = "CP_rol_description")
    private String CP_rol_description;

    @Column(name = "CP_rol_status")
    private String CP_rol_status;

}
