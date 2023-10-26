package com.portalClientesPrimadera.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.portalClientesPrimadera.model.ShoppingCartEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import com.portalClientesPrimadera.model.ShoppingCartLinesEntity;
import com.portalClientesPrimadera.repository.ShoppingCartLinesRepository;

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

    @GetMapping("/ShoppingCartLines/cartid/{cartid}")
    public List<ShoppingCartLinesEntity> getshoppingcartlinesbycartid(@PathVariable Long cartid) {
        return shoppingCartLinesRepository.findBycpcartid(cartid);
    }

    @GetMapping("/ShoppingCartLines/items/{cartid}")
    public List<ArrayList> getshoppingcartlinesitemsbycartid(@PathVariable Long cartid) {
        return shoppingCartLinesRepository.findByitemscpcartid(cartid);
    }

    @DeleteMapping("/ShoppingCartLines/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarlineacarrito(@PathVariable Long id){
        ShoppingCartLinesEntity shoppingCartLines = shoppingCartLinesRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("La linea del carrito de compra no se encuentra con el id " + id ));
        shoppingCartLinesRepository.delete(shoppingCartLines);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
