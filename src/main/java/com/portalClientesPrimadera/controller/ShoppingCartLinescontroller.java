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
import com.portalClientesPrimadera.model.ShoppingCartLinesEntity;
import com.portalClientesPrimadera.repository.ShoppingCartLinesRepository;

@CrossOrigin(origins =  "http://localhost:5173/")
@RestController
@RequestMapping("/api/v1")
public class ShoppingCartLinescontroller {
    
    @Autowired
    ShoppingCartLinesRepository shoppingCartLinesRepository;

    @GetMapping ("/ShoppingCartLines")
    public List <ShoppingCartLinesEntity> listShoppingCartLines(){
        return shoppingCartLinesRepository.findAll();
    }

    @PostMapping ("/ShoppingCartLines")
    public ShoppingCartLinesEntity saveShoppingCartLines(@RequestBody ShoppingCartLinesEntity ShoppingCartLines){
        return shoppingCartLinesRepository.save(ShoppingCartLines);
    }

    @GetMapping ("/ShoppingCartLines/{id}")
    public  ResponseEntity <ShoppingCartLinesEntity> getShoppingCartLinesid(@PathVariable Long id){
        ShoppingCartLinesEntity shoppingCartLines = shoppingCartLinesRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("La linea del carrito de compra no se encuentra con el id " + id ));
        return ResponseEntity.ok(shoppingCartLines);
    }

}
