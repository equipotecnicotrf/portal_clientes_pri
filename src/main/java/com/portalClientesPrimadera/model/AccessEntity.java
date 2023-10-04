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
@Table(name = "Access_table",uniqueConstraints = @UniqueConstraint(columnNames = "CP_Access_id"))
public class AccessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CP_Access_id;

    @Column(name = "CP_rol_id", nullable = false)
    private Long CP_rol_id;

    @Column(name = "CP_context_id", nullable = false)
    private Long CP_context_id;

    @Column(name = "CP_Access_assign", nullable = false, length =10)
    private Integer CP_access_assign;

}
