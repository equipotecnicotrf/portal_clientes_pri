package com.portalClientesPrimadera.controller;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import com.portalClientesPrimadera.model.AddressesEntity;
import com.portalClientesPrimadera.model.ItemsEntity;
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
    @GetMapping("/ShoppingCart/users")
    public List<ShoppingCartEntity> getShoppingCartByCustAccountIdAndCpUserId(
            @RequestParam(name = "Cust_account_id") Long custAccountId,
            @RequestParam(name = "CP_user_id") Long CP_user_id
    ) {
        return shoppinCartRepository.findByCustAccountIdAndCpUserId(custAccountId, CP_user_id);
    }

    @GetMapping("/ShoppingCart/linesanduser")
    public List<ArrayList> getShoppingCartlinesanduserByCustAccountIdAndCpUserId(
            @RequestParam(name = "Cust_account_id") Long custAccountId,
            @RequestParam(name = "CP_user_id") Long CP_user_id
    ) {
        return shoppinCartRepository.findByshopintcartAndLinesCustAccountIdAndCpUserId(custAccountId, CP_user_id);
    }






}
