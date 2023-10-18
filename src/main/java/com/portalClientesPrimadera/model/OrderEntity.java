package com.portalClientesPrimadera.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Order_table",uniqueConstraints = @UniqueConstraint(columnNames = "CP_order_id"))

public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CP_order_id;

    @Column(name = "CP_cart_id", nullable = false)
    private Long CP_cart_id;

    @Column(name = "CP_order_num", nullable = false)
    private String CP_order_num;

    @Column(name = "CP_user_id", nullable = false)
    private Long CP_user_id;

     @Column(name = "cust_account_id", nullable = false)
    private Long cust_account_id;

     @Column(name = "CP_order_status", nullable = false)
    private String CP_order_status;

}
