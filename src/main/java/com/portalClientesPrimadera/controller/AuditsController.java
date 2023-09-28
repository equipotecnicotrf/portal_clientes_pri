package com.portalClientesPrimadera.controller;

import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import com.portalClientesPrimadera.model.AuditEntity;
import com.portalClientesPrimadera.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins =  "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class AuditsController {

    @Autowired
    AuditRepository auditRepository;

    @GetMapping("/audits")
    public List<AuditEntity> getAuditList () {
        return auditRepository.findAll();
    }

    @PostMapping("/audits")
    public AuditEntity saveAudit (@RequestBody AuditEntity audit) {
        return auditRepository.save(audit);
    }

    @GetMapping("/audits/{CP_Audit_id}")
    public ResponseEntity<AuditEntity> getAuditByCP_Audit_id (@PathVariable Long CP_Audit_id){
        AuditEntity auditEntity = auditRepository.getCP_Audit_id(CP_Audit_id)
                .orElseThrow(()->new ResourceNotFoundException("El registro de auditoria no existe con el CP_Audit_id " + CP_Audit_id));
        return ResponseEntity.ok(auditEntity);
    }

}
