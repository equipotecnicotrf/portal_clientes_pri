package com.portalClientesPrimadera.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "shopping_cart_lines",uniqueConstraints = @UniqueConstraint(columnNames = "CP_cart_line_id"))

public class ShoppingCartLinesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CP_cart_line_id;

    @Column(name = "CP_cart_id", nullable = false)
    private Long CP_cart_id;

    @Column(name = "CP_cart_line_number", nullable = false)
    private Long CP_cart_line_number;

    @Column(name = "inventory_item_id", nullable = false)
    private Long inventory_item_id;

    @Column(name = "CP_cart_Quantity_units", nullable = false)
    private Long CP_cart_Quantity_units;

    @Column(name = "CP_cart_Quantity_packages", nullable = false)
    private Long CP_cart_Quantity_packages;

    @Column(name = "CP_cart_Quantity_volume", nullable = false)
    private Double CP_cart_Quantity_volume;
    
}
