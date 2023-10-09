package com.portalClientesPrimadera.model;
import java.sql.Date;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "IVA", uniqueConstraints = @UniqueConstraint(columnNames = "CP_IVA_id"))
public class IVAEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CP_IVA_id;

    @Column(name = "CP_IVA")
    private Long CP_IVA;

    @Column(name = "CP_IVA_date_start")
    private Date CP_IVA_date_start;

    @Column(name = "CP_IVA_date_end")
    private Date CP_IVA_date_end;

    
}
