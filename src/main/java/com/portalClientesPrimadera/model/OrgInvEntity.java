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
    private Integer organization_id;

    @Column(name = "organization_code", nullable = false, length = 30)
    private String organization_code;

    @Column(name = "organization_name", nullable = false, length = 30)
    private String organization_name;

    @Column(name = "organization_status", nullable = false, length = 30)
    private String organization_status;

}
