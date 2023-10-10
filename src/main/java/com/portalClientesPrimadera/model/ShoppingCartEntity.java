package com.portalClientesPrimadera.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "shopping_cart",uniqueConstraints = @UniqueConstraint(columnNames = "CP_cart_id"))

public class ShoppingCartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CP_cart_id;

    @Column(name = "CP_user_id", nullable = false)
    private Long CP_user_id;

    @Column(name = "cust_account_id", nullable = false)
    private Long cust_account_id;

    @Column(name = "site_use_id", nullable = false)
    private Long site_use_id;

    @Column(name = "CP_cart_status", nullable = false)
    private String CP_cart_status;
    
}
