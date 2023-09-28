package com.portalClientesPrimadera.controller;

import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import com.portalClientesPrimadera.model.RolesEntity;
import com.portalClientesPrimadera.model.TypeOrderEntity;
import com.portalClientesPrimadera.model.UsersEntity;
import com.portalClientesPrimadera.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins =  "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class RolesController {

    @Autowired
    RolesRepository rolesRepository;

    @GetMapping("/Roles")
    public List<RolesEntity> ListarRoles(){
        return rolesRepository.findAll();
    }

    @PostMapping("/Roles")
    public RolesEntity saveRol (@RequestBody RolesEntity rolesEntity){
        return rolesRepository.save(rolesEntity);
    }

    @GetMapping("/Roles/{CP_rol_id}")
    public ResponseEntity<RolesEntity> getRolByCP_rol_id(@PathVariable Long CP_rol_id) {
        RolesEntity rol = rolesRepository.getRolByCP_rol_id(CP_rol_id)
                .orElseThrow(()->new ResourceNotFoundException("El rol no existe con el CP_rol_id" + CP_rol_id));
        return ResponseEntity.ok(rol);
    }

}
