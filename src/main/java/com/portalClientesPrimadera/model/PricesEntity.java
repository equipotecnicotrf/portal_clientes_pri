package com.portalClientesPrimadera.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Prices", uniqueConstraints = @UniqueConstraint(columnNames = "Prices_id"))
public class PricesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Prices_id;

    @Column(name = "Cust_account_id", nullable = false)
    private Long Cust_account_id;

    @Column(name = "Inventory_item_id", nullable = false)
    private Long Inventory_item_id;

    @Column(name = "Organization_id", nullable = false)
    private Long Organization_id;

    @Column(name = "currency_code", nullable = false)
    private String currency_code;

    @Column(name = "unit_price", nullable = false)
    private Double unit_price;

    @Column(name = "CP_IVA_id", nullable = false)
    private Long CP_IVA_id;

}
