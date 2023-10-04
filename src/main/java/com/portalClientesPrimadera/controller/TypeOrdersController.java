package com.portalClientesPrimadera.controller;

import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import com.portalClientesPrimadera.model.TypeOrderEntity;
import com.portalClientesPrimadera.repository.TypeOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins =  "http://localhost:5173/")
@RestController
@RequestMapping("/api/v1")
public class TypeOrdersController {

    @Autowired
    TypeOrderRepository typeOrderRepository;

    @GetMapping("/TypeOrders")
    public List<TypeOrderEntity> ListarTypeOrders(){
        return typeOrderRepository.findAll();
    }

    @PostMapping("/TypeOrders")
    public TypeOrderEntity saveTypeOrder (@RequestBody TypeOrderEntity typeOrderEntity){
        return typeOrderRepository.save(typeOrderEntity);
    }

    @GetMapping("/TypeOrders/{id}")
    public ResponseEntity<TypeOrderEntity> getTypeOrderById(@PathVariable Long id) {
        TypeOrderEntity typeOrder = typeOrderRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("El type order no existe con el CP_type_order_id" + id ));
        return ResponseEntity.ok(typeOrder);
    }
}
