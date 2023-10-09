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

    @Column(name = "CP_order_id")
    private Long CP_order_id;

    @Column(name = "CP_order_line_number")
    private Long CP_order_line_number;

    @Column(name = "inventory_item_id")
    private Long inventory_item_id;

    @Column(name = "CP_order_Quantity_units")
    private Long CP_order_Quantity_units;

    @Column(name = "CP_order_Quantity_packages")
    private Long CP_order_Quantity_packages;

    @Column(name = "CP_order_Quantity_volume")
    private Long CP_order_Quantity_volume;
    
}
