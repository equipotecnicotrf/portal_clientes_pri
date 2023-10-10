package com.portalClientesPrimadera.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Availability", uniqueConstraints = @UniqueConstraint(columnNames = "inventory_item_id"))
public class AvailabilityEntity {

    @Id
    @Column(name = "inventory_item_id", nullable = false)
    private Long inventory_item_id;

    @Column(name = "organization_id", nullable = false)
    private Long organization_id;

    @Column(name = "organization_code", nullable = false)
    private String organization_code;

    @Column(name = "Quantity_units", nullable = false)
    private Long Quantity_units;

    @Column(name = "Quantity_packages", nullable = false)
    private Long Quantity_packages;

    
}
