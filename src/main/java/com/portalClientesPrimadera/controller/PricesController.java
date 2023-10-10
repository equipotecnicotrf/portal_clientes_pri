package com.portalClientesPrimadera.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import com.portalClientesPrimadera.model.PricesEntity;
import com.portalClientesPrimadera.repository.PricesRepository;

@CrossOrigin(origins =  "http://localhost:5173/")
@RestController
@RequestMapping("/api/v1")
public class PricesController {
    @Autowired
    PricesRepository pricesRepository;

    @GetMapping ("/Prices")
    public List <PricesEntity> listarPrices(){
        return pricesRepository.findAll();
    }

    @PostMapping ("/Prices")

    public PricesEntity savePrice (@RequestBody PricesEntity Prices){
        return pricesRepository.save(Prices);
    }

    @GetMapping ("/Prices/{id}")
    public ResponseEntity <PricesEntity> GetPricesById(@PathVariable Long id){
        PricesEntity Prices = pricesRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("El precio no se encuentra con el id " + id));
        return ResponseEntity.ok(Prices);
    }


}
