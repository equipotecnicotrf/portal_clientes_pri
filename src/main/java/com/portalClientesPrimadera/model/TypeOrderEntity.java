package com.portalClientesPrimadera.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Type_order", uniqueConstraints = {@UniqueConstraint(columnNames = "CP_type_order_id"), @UniqueConstraint(columnNames = "CP_type_order_meaning"), @UniqueConstraint(columnNames = "CP_type_order_description")})
public class TypeOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CP_type_order_id;

    @Column(name = "CP_type_order_meaning", nullable = false)
    private String CP_type_order_meaning;

    @Column(name = "CP_type_order_description", nullable = false)
    private String CP_type_order_description;

    @Column(name = "CP_type_order_status", nullable = false)
    private String CP_type_order_status;

}
