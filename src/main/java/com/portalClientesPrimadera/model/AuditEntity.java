package com.portalClientesPrimadera.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Audit_table",uniqueConstraints = @UniqueConstraint(columnNames = "CP_Audit_id"))
public class AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CP_Audit_id;

    @Column(name = "CP_id_user") ///cp_user_id
    private Integer CP_id_user;

    @Column(name = "CP_Audit_description")
    private String CP_audit_description;

    @Column(name = "CP_Audit _Date" )
    private Date CP_audit_date;

    @PrePersist
    protected void onCreate(){
        CP_audit_date = new Date();
    }

}
