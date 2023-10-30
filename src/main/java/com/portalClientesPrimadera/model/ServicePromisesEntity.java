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
@Table(name = "Service_Promises", uniqueConstraints = @UniqueConstraint(columnNames = "CP_id_promises"))
public class ServicePromisesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CP_id_promises;

    @Column(name = "CP_type_promise", nullable = false)
    private String CP_type_promise;

    @Column(name = "CP_Description_promise", nullable = false)
    private String CP_description_promise;

}
