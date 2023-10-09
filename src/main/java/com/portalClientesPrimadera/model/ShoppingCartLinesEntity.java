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

    @Column(name = "CP_cart_id")
    private Long CP_cart_id;

    @Column(name = "CP_cart_line_number")
    private Long CP_cart_line_number;

    @Column(name = "inventory_item_id")
    private Long inventory_item_id;

    @Column(name = "CP_cart_Quantity_units")
    private Long CP_cart_Quantity_units;

    @Column(name = "CP_cart_Quantity_packages")
    private Long CP_cart_Quantity_packages;

    @Column(name = "CP_cart_Quantity_volume")
    private Long CP_cart_Quantity_volume;
    
}
