package com.portalClientesPrimadera.model;

import java.sql.Date;

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
@Table(name = "Consecutive", uniqueConstraints = @UniqueConstraint(columnNames = "CP_Consecutive_id"))
public class ConsecutiveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CP_Consecutive_id;

    @Column(name = "CP_Consecutive_code", nullable = false)
    private String CP_Consecutive_code;

    @Column(name = "CP_Consecutive_num", nullable = false)
    private Long CP_Consecutive_num;

      @Column(name = "CP_Consecutive_date_start", nullable = false)
    private Date CP_Consecutive_date_start;

      @Column(name = "CP_Consecutive_date_end", nullable = false)
    private Date CP_Consecutive_date_end;
    
}
