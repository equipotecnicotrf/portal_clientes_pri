package com.portalClientesPrimadera.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Type_order",uniqueConstraints = @UniqueConstraint(columnNames = "CP_type_order_id"))
public class TypeOrderEntity {

    @Id
    @Column(name = "CP_type_order_id", nullable = false, length =10)
    private Integer CP_type_order_id;

    @Column(name = "CP_type_order_description", nullable = false, length = 30)
    private String CP_type_order_description;

    @Column(name = "CP_type_order_meaning",nullable = false, length =30)
        private String CP_type_order_meaning;

}
