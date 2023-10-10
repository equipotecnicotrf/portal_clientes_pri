package com.portalClientesPrimadera.controller;

import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import com.portalClientesPrimadera.model.TypeOrderEntity;
import com.portalClientesPrimadera.repository.TypeOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TypeOrdersController {

    @Autowired
    TypeOrderRepository typeOrderRepository;

    @GetMapping("/TypeOrders")
    public List<TypeOrderEntity> ListarTypeOrders() {
        return typeOrderRepository.findAll();
    }

    @PostMapping("/TypeOrders")
    public TypeOrderEntity saveTypeOrder(@RequestBody TypeOrderEntity typeOrderEntity) {
        return typeOrderRepository.save(typeOrderEntity);
    }

    @GetMapping("/TypeOrders/{id}")
    public ResponseEntity<TypeOrderEntity> getTypeOrderById(@PathVariable Long id) {
        TypeOrderEntity typeOrder = typeOrderRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("El type order no existe con el CP_type_order_id" + id));
        return ResponseEntity.ok(typeOrder);
    }

    @PutMapping("/TypeOrders/{id}")
    public ResponseEntity<TypeOrderEntity> actualizarTypeOrderPorId(@PathVariable Long id,
            @RequestBody TypeOrderEntity usersRequest) {
        TypeOrderEntity typeorder = typeOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El tipo de pedido con este ID no existe : " + id));
        typeorder.setCP_type_order_description(usersRequest.getCP_type_order_description());
        typeorder.setCP_type_order_meaning(usersRequest.getCP_type_order_meaning());
        typeorder.setCP_type_order_status(usersRequest.getCP_type_order_status());

        TypeOrderEntity typeOrderActualizado = typeOrderRepository.save(typeorder);
        return ResponseEntity.ok(typeOrderActualizado);
    }

}
