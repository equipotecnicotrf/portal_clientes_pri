package com.portalClientesPrimadera.controller;



import java.util.List;

import com.portalClientesPrimadera.model.OrderLinesEntity;
import com.portalClientesPrimadera.model.ShoppingCartLinesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import com.portalClientesPrimadera.model.OrderEntity;
import com.portalClientesPrimadera.repository.OrderRepository;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    OrderRepository orderRepository; 
    
    @GetMapping ("/Order")
    public List <OrderEntity> listOrders(){
        return orderRepository.findAll();
    }

    @PostMapping ("/Order")
    public OrderEntity saveOrder (@RequestBody OrderEntity Order){
        return orderRepository.save(Order);
    }

    @GetMapping ("/Order/{id}")
    public ResponseEntity <OrderEntity> getOrderByid(@PathVariable Long id){
        OrderEntity order = orderRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("La orden no se encuentra con el id "+ id ));
        return ResponseEntity.ok(order);
    }

    @PutMapping("/Order/{id}")
    public ResponseEntity<OrderEntity> actualizarOrderPorId(@PathVariable Long id,
                                                                     @RequestBody OrderEntity orderRequest) {
        OrderEntity orderupdate = orderRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("La orden no se encuentra con el id " + id));
        orderupdate.setCP_order_status(orderRequest.getCP_order_status());

        OrderEntity orderActualizada = orderRepository.save(orderupdate);
        return ResponseEntity.ok(orderActualizada);
    }

    @GetMapping("/Order/cartid/{cartid}")
    public List<OrderEntity> getOrderBycartid(@PathVariable Long cartid) {
        return orderRepository.findByordercpcartid(cartid);
    }

}
