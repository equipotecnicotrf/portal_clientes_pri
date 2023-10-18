package com.portalClientesPrimadera.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Users", uniqueConstraints = @UniqueConstraint(columnNames = "CP_username"))
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CP_user_id;

    @Column(name = "CP_username", nullable = false)
    private String username;

    @Column(name = "CP_Password", nullable = false)
    private String CP_Password;

    @Column(name = "CP_name", nullable = false)
    private String CP_name;

    @Column(name = "CP_email", nullable = false)
    private String CP_email;

    @Column(name = "cust_account_id", nullable = false)
    private Long cust_account_id;

    @Column(name = "cust_name", nullable = false)
    private String cust_name;

    @Column(name = "CP_rol_id", nullable = false)
    private Long CP_rol_id;

    @Column(name = "CP_estatus", nullable = false)
    private String CP_estatus;

    @Column(name = "CP_cell_phone", nullable = false)
    private String CP_cell_phone;

    @Column(name = "party_id", nullable = false)
    private Long party_id;

}