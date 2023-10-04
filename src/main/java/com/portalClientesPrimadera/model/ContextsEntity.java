package com.portalClientesPrimadera.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Context",uniqueConstraints = @UniqueConstraint(columnNames = "CP_context_id"))
public class ContextsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CP_context_id;

    @Column(name = "CP_context_name")
    private String CP_context_name;

    @Column(name = "CP_context_description")
    private String CP_context_description;

}
