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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventory_item_id;

    @Column(name = "organization_id")
    private Long organization_id;

    @Column(name = "organization_code")
    private String organization_code;

    @Column(name = "Quantity_units")
    private Long Quantity_units;

    @Column(name = "Quantity_packages")
    private Long Quantity_packages;

    
}
