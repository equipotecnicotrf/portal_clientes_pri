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
    @Column(name = "inventory_item_id")
    private Long inventory_item_id;

    @Column(name = "item_number")
    private String item_number;

    @Column(name = "item_description")
    private String item_description;

    @Column(name = "uom_code")
    private String uom_code;

     @Column(name = "Unit_of_measure")
    private String Unit_of_measure;

     @Column(name = "Atribute1")
    private String Atribute1;

     @Column(name = "Atribute2")
    private String Atribute2;

     @Column(name = "Atribute3")
    private String Atribute3;

     @Column(name = "Atribute4")
    private String Atribute4;

     @Column(name = "Atribute5")
    private String Atribute5;

     @Column(name = "Atribute6")
    private String Atribute6;

     @Column(name = "Atribute7")
    private String Atribute7;

     @Column(name = "Atribute8")
    private Long Atribute8;


}
