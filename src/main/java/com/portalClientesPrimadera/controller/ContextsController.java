package com.portalClientesPrimadera.controller;

import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import com.portalClientesPrimadera.model.AuditEntity;
import com.portalClientesPrimadera.model.ContextsEntity;
import com.portalClientesPrimadera.repository.ContextsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins =  "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class ContextsController {

    @Autowired
    ContextsRepository contextRepository;

    @GetMapping("/Contexts")
    public List<ContextsEntity> getContextsList () {
        return contextRepository.findAll();
    }

    @PostMapping("/Contexts")
    public ContextsEntity saveContext(@RequestBody ContextsEntity context) {
        return contextRepository.save(context);
    }

    @GetMapping("/Contexts/{CP_context_id}")
    public ResponseEntity<ContextsEntity> getAuditByCP_context_id(@PathVariable Long CP_context_id) {
        ContextsEntity context = contextRepository.getCP_context_id(CP_context_id)
                .orElseThrow(() -> new ResourceNotFoundException("El contexto no existe con el CP_context_id " + CP_context_id));
        return ResponseEntity.ok(context);
    }


}
