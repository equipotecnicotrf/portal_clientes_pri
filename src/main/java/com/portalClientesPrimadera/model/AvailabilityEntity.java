package com.portalClientesPrimadera.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Availability", uniqueConstraints = @UniqueConstraint(columnNames = "CP_id_item_inventory"))
public class AvailabilityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CP_id_item_inventory;

    @Column(name = "inventory_item_id", nullable = false)
    private Long inventory_item_id;

    @Column(name = "organization_id", nullable = false)
    private Long organization_id;

    @Column(name = "organization_code", nullable = false)
    private String organization_code;

    @Column(name = "Quantity_on_hand", nullable = false)
    private Long Quantity_on_hand;

    @Column(name = "Available_to_transact")
    private Long Available_to_transact;

    
}
