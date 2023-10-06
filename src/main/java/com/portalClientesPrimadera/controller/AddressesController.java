package com.portalClientesPrimadera.controller;

import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import com.portalClientesPrimadera.model.AddressesEntity;
import com.portalClientesPrimadera.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins =  "http://localhost:5173/")
@RestController
@RequestMapping("/api/v1")
public class AddressesController {

    @Autowired
    AddressRepository addressRepository;

    @GetMapping("/Addresses")
    public List<AddressesEntity> ListarAdresses(){
        return addressRepository.findAll();
    }

    @PostMapping("/Addresses")
    public AddressesEntity saveAddresses(@RequestBody AddressesEntity Address){
        return addressRepository.save(Address);
    }

    @GetMapping("/Addresses/{id}")
    public ResponseEntity<AddressesEntity> getAddressesBySiteId(@PathVariable Long id){
        AddressesEntity address = addressRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("La dirección por este site_use_id no existe" + id));
        return ResponseEntity.ok(address);
    }

}
