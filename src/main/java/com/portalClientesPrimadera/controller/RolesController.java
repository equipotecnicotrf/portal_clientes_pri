package com.portalClientesPrimadera.controller;

import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import com.portalClientesPrimadera.model.RolesEntity;
import com.portalClientesPrimadera.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RolesController {

    @Autowired
    RolesRepository rolesRepository;

    @GetMapping("/Roles")
    public List<RolesEntity> ListarRoles() {
        return rolesRepository.findAll();
    }

    @PostMapping("/Roles")
    public RolesEntity saveRol(@RequestBody RolesEntity rolesEntity) {
        return rolesRepository.save(rolesEntity);
    }

    @GetMapping("/Roles/{id}")
    public ResponseEntity<RolesEntity> getRolByCP_rol_id(@PathVariable Long id) {
        RolesEntity rol = rolesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El rol no existe con el CP_rol_id" + id));
        return ResponseEntity.ok(rol);
    }

    @PutMapping("/Roles/{id}")
    public ResponseEntity<RolesEntity> actualizarRolPorId(@PathVariable Long id, @RequestBody RolesEntity roleRequest) {
        RolesEntity rol = rolesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El rol con este ID no existe : " + id));
        rol.setCP_rol_name(roleRequest.getCP_rol_name());
        rol.setCP_rol_description(roleRequest.getCP_rol_description());
        rol.setCP_rol_status(roleRequest.getCP_rol_status());

        RolesEntity RolActualizado = rolesRepository.save(rol);
        return ResponseEntity.ok(RolActualizado);
    }

}
