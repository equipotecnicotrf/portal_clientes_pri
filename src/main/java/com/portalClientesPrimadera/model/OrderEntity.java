package com.portalClientesPrimadera.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Order",uniqueConstraints = @UniqueConstraint(columnNames = "CP_order_id"))
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CP_order_id;

    @Column(name = "CP_cart_id")
    private Long CP_cart_id;

    @Column(name = "CP_order_num")
    private String CP_order_num;

    @Column(name = "CP_user_id")
    private Long CP_user_id;

     @Column(name = "cust_account_id")
    private Long cust_account_id;

     @Column(name = "CP_order_status")
    private String CP_order_status;

}
