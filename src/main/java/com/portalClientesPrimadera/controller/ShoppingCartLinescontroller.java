package com.portalClientesPrimadera.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.portalClientesPrimadera.model.NotificationsEntity;
import com.portalClientesPrimadera.model.ShoppingCartEntity;
import com.portalClientesPrimadera.model.UsersEntity;
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

    @GetMapping("/ShoppingCartLines/items/")
    public List<ArrayList> getshoppingcartlinesitemsbycartid(@RequestParam (name = "cartid") Long cp_cart_id,
                                                             @RequestParam(name = "Cust_account_id") Long custAccountId) {
        return shoppingCartLinesRepository.findByitemscpcartid(cp_cart_id, custAccountId);
    }

    @DeleteMapping("/ShoppingCartLines/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarlineacarrito(@PathVariable Long id){
        ShoppingCartLinesEntity shoppingCartLines = shoppingCartLinesRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("La linea del carrito de compra no se encuentra con el id " + id ));
        shoppingCartLinesRepository.delete(shoppingCartLines);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/ShoppingCartLines/{id}")
    public ResponseEntity<ShoppingCartLinesEntity> actualizarShoplinePorId(@PathVariable Long id,
                                                                   @RequestBody ShoppingCartLinesEntity shoplinesRequest) {
        ShoppingCartLinesEntity shoppingCartLines = shoppingCartLinesRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("La linea del carrito de compra no se encuentra con el id " + id ));
        shoppingCartLines.setCP_cart_Quantity_packages(shoplinesRequest.getCP_cart_Quantity_packages());
        shoppingCartLines.setCP_cart_Quantity_volume(shoplinesRequest.getCP_cart_Quantity_volume());
        shoppingCartLines.setCP_cart_Quantity_units(shoplinesRequest.getCP_cart_Quantity_units());

        ShoppingCartLinesEntity shoplinesActualizada = shoppingCartLinesRepository.save(shoppingCartLines);
        return ResponseEntity.ok(shoplinesActualizada);
    }
}
