package com.portalClientesPrimadera.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "order_lines",uniqueConstraints = @UniqueConstraint(columnNames = "CP_order_line_id"))

public class OrderLinesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CP_order_line_id;

    @Column(name = "CP_order_id", nullable = false)
    private Long CP_order_id;

    @Column(name = "CP_order_line_number", nullable = false)
    private Long CP_order_line_number;

    @Column(name = "inventory_item_id", nullable = false)
    private Long inventory_item_id;

    @Column(name = "CP_order_Quantity_units", nullable = false)
    private Long CP_order_Quantity_units;

    @Column(name = "CP_order_Quantity_packages", nullable = false)
    private Long CP_order_Quantity_packages;

    @Column(name = "CP_order_Quantity_volume", nullable = false)
    private Double CP_order_Quantity_volume;
    
}
