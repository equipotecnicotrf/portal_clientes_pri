package com.portalClientesPrimadera.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Type_order", uniqueConstraints = @UniqueConstraint(columnNames = "CP_type_order_id"))
public class TypeOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CP_type_order_id;

    @Column(name = "CP_type_order_description")
    private String CP_type_order_description;

    @Column(name = "CP_type_order_meaning")
    private String CP_type_order_meaning;

    @Column(name = "CP_type_order_status")
    private String CP_type_order_status;

}
