package com.portalClientesPrimadera.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import com.portalClientesPrimadera.model.ShoppingCartEntity;
import com.portalClientesPrimadera.repository.ShoppinCartRepository;



@RestController
@RequestMapping("/api/v1")
public class ShoppingCartController {
    
    @Autowired
    ShoppinCartRepository shoppinCartRepository;

    @GetMapping ("/ShoppingCart")
        public List <ShoppingCartEntity> listShoppingCart(){
            return shoppinCartRepository.findAll();
        }
    
    @PostMapping ("/ShoppingCart")
    public ShoppingCartEntity saveShoppingCart (@RequestBody ShoppingCartEntity ShoppingCart){
        return shoppinCartRepository.save(ShoppingCart);
    }

    @GetMapping ("/ShoppingCart/{id}")
    public ResponseEntity <ShoppingCartEntity> getShoppingCartByid(@PathVariable Long id){
        ShoppingCartEntity shoppingCart = shoppinCartRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("El carrito de compra no se encontro con el id "+ id));
        return ResponseEntity.ok(shoppingCart);
    }



    
    
}
