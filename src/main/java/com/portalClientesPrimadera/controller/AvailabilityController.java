package com.portalClientesPrimadera.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import com.portalClientesPrimadera.model.AvailabilityEntity;
import com.portalClientesPrimadera.repository.AvailabilityRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins =  "http://localhost:5173/")
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

}
