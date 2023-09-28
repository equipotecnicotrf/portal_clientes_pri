package com.portalClientesPrimadera.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Users",uniqueConstraints = @UniqueConstraint(columnNames = "CP_username"))
public class UsersEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CP_user_id;

    @Column(name = "CP_username")
    private String username;

    @Column(name = "CP_Password")
    private String CP_Password;

    @Column(name = "CP_name")
    private String CP_name;

    @Column(name = "CP_email")
    private String CP_email;

    @Column(name = "cust_account_id")
    private Integer cust_account_id;

    @Column(name = "cust_name")
    private String cust_name;

    @Column(name = "CP_rol_id")
    private Integer CP_rol_id;

    @Column(name = "CP_estatus")
    private String CP_estatus;

    @Column(name = "CP_cell_phone")
    private Integer CP_cell_phone;

    @Column(name = "party_id")
    private Integer party_id;



}
