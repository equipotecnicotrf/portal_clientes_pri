package com.portalClientesPrimadera.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
