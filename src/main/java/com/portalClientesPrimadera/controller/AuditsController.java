package com.portalClientesPrimadera.controller;

import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import com.portalClientesPrimadera.model.AuditEntity;
import com.portalClientesPrimadera.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AuditsController {

    @Autowired
    AuditRepository auditRepository;

    @GetMapping("/audits")
    public List<AuditEntity> getAuditList() {
        return auditRepository.findAll();
    }

    @PostMapping("/audits")
    public AuditEntity saveAudit(@RequestBody AuditEntity audit) {
        return auditRepository.save(audit);
    }

    @GetMapping("/audits/{id}")
    public ResponseEntity<AuditEntity> getAuditByCP_Audit_id(@PathVariable Long id) {
        AuditEntity auditEntity = auditRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El registro de auditoria no existe con el CP_Audit_id " + id));
        return ResponseEntity.ok(auditEntity);
    }

    @GetMapping("/auditsanduser")
    public List<ArrayList> ListarAuditAndUser() {
        return auditRepository.Finbyauditanduser();
    }

}
