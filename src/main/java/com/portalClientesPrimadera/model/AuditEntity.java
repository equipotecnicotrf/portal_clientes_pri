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

    @Column(name = "CP_id_user", nullable = false)
    private Integer CP_id_user;

    @Column(name = "CP_Audit_description", nullable = false, length = 30)
    private String CP_audit_description;

    @Column(name = "CP_Audit _Date" )
    private Date CP_audit_date;

    @PrePersist
    protected void onCreate(){
        CP_audit_date = new Date();
    }


    /*
    @ManyToOne
    /* name="CP_id_user" columna de la entidad actual,
    referencedColumnName = "CP_id_user" hace referencia a la columna de otra tabla
    *//*
    @JoinColumn(name="CP_id_user", referencedColumnName = "CP_user_id", nullable = false )
    @Getter
    @Setter
    private UsersEntity usersEntity;*/

}
