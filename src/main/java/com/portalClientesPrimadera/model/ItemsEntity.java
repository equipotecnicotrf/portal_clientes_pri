package com.portalClientesPrimadera.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Items",uniqueConstraints = @UniqueConstraint(columnNames = "inventory_item_id"))
public class ItemsEntity {

    @Id
    @Column(name = "inventory_item_id", nullable = false)
    private Long inventory_item_id;

    @Column(name = "item_number", nullable = false)
    private String item_number;

    @Column(name = "item_description", nullable = false)
    private String item_description;

    @Column(name = "item_description_long", nullable = false)
    private String item_description_long;

    @Column(name = "uom_code", nullable = false)
    private String uom_code;

     @Column(name = "Unit_of_measure", nullable = false)
    private String Unit_of_measure;

     @Column(name = "Atribute1", nullable = false)
    private String Atribute1;

     @Column(name = "Atribute2", nullable = false)
    private String Atribute2;

     @Column(name = "Atribute3", nullable = false)
    private String Atribute3;

     @Column(name = "Atribute4", nullable = false)
    private String Atribute4;

     @Column(name = "Atribute5", nullable = false)
    private String Atribute5;

     @Column(name = "Atribute6", nullable = false)
    private String Atribute6;

     @Column(name = "Atribute7", nullable = false)
    private String Atribute7;

     @Column(name = "Atribute8", nullable = false)
    private Long Atribute8;

    @Column(name = "Atribute9", nullable = false)
    private Long Atribute9;

}
