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
@Table(name = "Org_inv",uniqueConstraints = @UniqueConstraint(columnNames = "organization_id"))
public class OrgInvEntity {

    @Id
    @Column(name = "organization_id")
    private Long organization_id;

    @Column(name = "organization_code")
    private String organization_code;

    @Column(name = "organization_name")
    private String organization_name;

    @Column(name = "organization_status")
    private String organization_status;

}
