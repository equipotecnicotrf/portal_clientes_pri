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
    @Column(name = "site_use_id", nullable = false)
    private Long site_use_id;

    @Column(name = "cust_account_id", nullable = false)
    private Long cust_account_id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "Sales_person_code", nullable = false)
    private Long Sales_person_code;

    @Column(name = "Sales_person_name", nullable = false)
    private String Sales_person_name;

    @Column(name = "Site_use_code", nullable = false)
    private String Site_use_code;


    @Column(name = "organization_id", nullable = false)
    private Long organization_id;

    @Column(name = "party_site_id", nullable = false)
    private Long party_site_id;

}


