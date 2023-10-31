package com.portalClientesPrimadera.controller;

import java.util.List;

import com.portalClientesPrimadera.model.ShoppingCartEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import com.portalClientesPrimadera.model.AvailabilityEntity;
import com.portalClientesPrimadera.repository.AvailabilityRepository;

@RestController
@RequestMapping("/api/v1")
public class AvailabilityController {
     @Autowired
    AvailabilityRepository availabilityRepository;

    @GetMapping ("/Availability")
    public List <AvailabilityEntity> listarPrices(){
        return availabilityRepository.findAll();
    }

    @PostMapping ("/Availability")

    public AvailabilityEntity savePrice (@RequestBody AvailabilityEntity Availability){
        return availabilityRepository.save(Availability);
    }

    @GetMapping ("/Availability/{id}")
    public ResponseEntity <AvailabilityEntity> GetPricesById(@PathVariable Long id){
        AvailabilityEntity Availability = availabilityRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("El Availability no se encuentra con el id " + id));
        return ResponseEntity.ok(Availability);
    }

    @GetMapping("/Availability/Item")
    public List<AvailabilityEntity> getAvailabilityItem(
            @RequestParam(name = "inventory_item_id") Long inventory_item_id ) {
        return availabilityRepository.findByinventoryitemid(inventory_item_id);
    }

}
