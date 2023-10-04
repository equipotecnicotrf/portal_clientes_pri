package com.portalClientesPrimadera.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Addresses",uniqueConstraints = @UniqueConstraint(columnNames = "site_use_id"))
public class AddressesEntity {
    @Id
    private Integer site_use_id;

    @Column(name = "cust_account_id")
    private Integer cust_account_id;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "department")
    private String department;

    @Column(name = "Sales_person_code")
    private String Sales_person_code;

    @Column(name = "Sales_person_name")
    private String Sales_person_name;

    @Column(name = "Site_use_code")
    private String Site_use_code;

    @Column(name = "CP_type_order_id")
    private Integer CP_type_order_id;

    @Column(name = "organization_id")
    private Integer organization_id;

    @Column(name = "party_site_id")
    private Integer party_site_id;




}


