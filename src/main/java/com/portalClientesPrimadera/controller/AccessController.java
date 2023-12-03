package com.portalClientesPrimadera.controller;

import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import com.portalClientesPrimadera.model.AccessEntity;
import com.portalClientesPrimadera.repository.AccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class AccessController {

    @Autowired
    AccessRepository accessRepository;

    @GetMapping("/Access")
    public List<AccessEntity> ListarAccess() {
        return accessRepository.findAll();
    }

    @PostMapping("/Access")
    public AccessEntity saveAccess(@RequestBody AccessEntity accessEntity) {
        return accessRepository.save(accessEntity);
    }

    @GetMapping("/Access/{id}")
    public ResponseEntity<AccessEntity> getAccessByCP_Access_id(@PathVariable Long id) {
        AccessEntity access = accessRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El acceso no existe con el CP_Access_id" + id));
        return ResponseEntity.ok(access);
    }

    @GetMapping("/AccessAndContext")
    public List<ArrayList> getAllAccessandContext(@RequestParam(name = "cp_rol_id") Long CP_rol_id) {
        return accessRepository.findByAccessandContext(CP_rol_id);
    }

}
