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

    @Column(name = "cust_account_id", length = 10, insertable = false,  updatable = false)
    private Integer cust_account_id;

    @Column(name = "address", nullable = false, length = 30)
    private String address;

    @Column(name = "city", nullable = false, length = 30)
    private String city;

    @Column(name = "department", nullable = false, length = 30)
    private String department;

    @Column(name = "Sales_person_code", nullable = false, length = 30)
    private String Sales_person_code;

    @Column(name = "Sales_person_name", nullable = false, length = 30)
    private String Sales_person_name;

    @Column(name = "Site_use_code", nullable = false, length = 30)
    private String Site_use_code;

    @Column(name = "CP_type_order_id", length = 10, insertable = false,  updatable = false)
    private Integer CP_type_order_id;

    @Column(name = "organization_id", nullable = false, length = 10)
    private Integer organization_id;

    @Column(name = "party_site_id", nullable = false, length = 10)
    private Integer party_site_id;




}


