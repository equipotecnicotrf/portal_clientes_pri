package com.portalClientesPrimadera.controller;

import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import com.portalClientesPrimadera.model.PortalStatusEntity;
import com.portalClientesPrimadera.model.RolesEntity;
import com.portalClientesPrimadera.repository.PortalStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins =  "http://localhost:3000")
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

    @GetMapping("/PortalStatus/{CP_portal_id}")
    public ResponseEntity<PortalStatusEntity> getPortalStatusByCP_portal_id (@PathVariable Long CP_portal_id){
        PortalStatusEntity portalStatus = portalStatusRepository.getPortalStatusById(CP_portal_id)
                .orElseThrow(()->new ResourceNotFoundException("El portal status no existe con el CP_portal_id " + CP_portal_id));
        return ResponseEntity.ok(portalStatus);
    }

}
