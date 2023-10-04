package com.portalClientesPrimadera.controller;

import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import com.portalClientesPrimadera.model.PortalStatusEntity;
import com.portalClientesPrimadera.model.RolesEntity;
import com.portalClientesPrimadera.repository.PortalStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins =  "http://localhost:5173/")
@RestController
@RequestMapping("/api/v1")
public class PortalStatusController {

    @Autowired
    PortalStatusRepository portalStatusRepository;

    @GetMapping("/PortalStatus")
    public List<PortalStatusEntity> ListarPortalStatus(){
        return portalStatusRepository.findAll();
    }

    @PostMapping("/PortalStatus")
    public PortalStatusEntity savePortalStatus (@RequestBody PortalStatusEntity PortalStatusEntity){
        return portalStatusRepository.save(PortalStatusEntity);
    }

    @GetMapping("/PortalStatus/{id}")
    public ResponseEntity<PortalStatusEntity> getPortalStatusByCP_portal_id (@PathVariable Long id){
        PortalStatusEntity portalStatus = portalStatusRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("El portal status no existe con el CP_portal_id " + id));
        return ResponseEntity.ok(portalStatus);
    }

}
