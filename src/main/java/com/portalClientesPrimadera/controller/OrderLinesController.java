package com.portalClientesPrimadera.controller;

import java.util.List;

import com.portalClientesPrimadera.model.OrderEntity;
import com.portalClientesPrimadera.model.ShoppingCartLinesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import com.portalClientesPrimadera.model.OrderLinesEntity;
import com.portalClientesPrimadera.repository.OrderLinesRepository;

@RestController
@RequestMapping("/api/v1")
public class OrderLinesController {

    @Autowired
    OrderLinesRepository orderLinesRepository;

    @GetMapping ("/OrderLines")
    public List <OrderLinesEntity> listOrderLines(){
        return orderLinesRepository.findAll();
    }
     
    @PostMapping ("/OrderLines")
    public OrderLinesEntity saveOrderLines (@RequestBody OrderLinesEntity OrderLines){
        return orderLinesRepository.save(OrderLines);
    }

    @GetMapping ("/OrderLines/{id}")
    public ResponseEntity <OrderLinesEntity> getOrderLinesByid(@PathVariable Long id){
        OrderLinesEntity orderLines = orderLinesRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("La Linea de orden no se encuentra con el id " + id));
        return ResponseEntity.ok(orderLines);
    }

    @PutMapping("/OrderLines/{id}")
    public ResponseEntity<OrderLinesEntity> actualizarOrderlinePorId(@PathVariable Long id,
                                                                       @RequestBody OrderLinesEntity orderlinesRequest) {
        OrderLinesEntity orderLines = orderLinesRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("La Linea de orden no se encuentra con el id " + id));
        orderLines.setCP_order_Quantity_packages(orderlinesRequest.getCP_order_Quantity_packages());
        orderLines.setCP_order_Quantity_volume(orderlinesRequest.getCP_order_Quantity_volume());
        orderLines.setCP_order_Quantity_units(orderlinesRequest.getCP_order_Quantity_units());
        orderLines.setCP_line_order_status(orderlinesRequest.getCP_line_order_status());

        OrderLinesEntity orderlinesActualizada = orderLinesRepository.save(orderLines);
        return ResponseEntity.ok(orderlinesActualizada);
    }

    @GetMapping("/OrderLines/cartlineid/{cartlineid}")
    public List<OrderLinesEntity> getOrderBycartid(@PathVariable Long cartlineid) {
        return orderLinesRepository.findByordercpcartlineid(cartlineid);
    }

}
