package com.portalClientesPrimadera.controller;

import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import com.portalClientesPrimadera.model.OrgInvEntity;
import com.portalClientesPrimadera.repository.OrgInvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins =  "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class OrgInvController {

    @Autowired
    OrgInvRepository orgInvRepository;

    @GetMapping("/OrgInv")
    public List<OrgInvEntity> getOrgInvList () {
        return orgInvRepository.findAll();
    }

    @PostMapping("/OrgInv")
    public OrgInvEntity saveOrgInv (@RequestBody OrgInvEntity orgInv){
        return orgInvRepository.save(orgInv);
    }

    @GetMapping("/OrgInv/{organization_id}")
    public ResponseEntity<OrgInvEntity> getOrgInvByOrgId(@PathVariable Integer organization_id){
        OrgInvEntity orgInv = orgInvRepository.getorganization_id(organization_id)
                .orElseThrow(()->new ResourceNotFoundException("La organización de inventario con el organization_id no existe"));
        return ResponseEntity.ok(orgInv);
    }

}
