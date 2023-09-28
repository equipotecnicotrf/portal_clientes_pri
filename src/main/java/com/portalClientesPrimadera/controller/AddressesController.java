package com.portalClientesPrimadera.controller;

import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import com.portalClientesPrimadera.model.AddressesEntity;
import com.portalClientesPrimadera.model.UsersEntity;
import com.portalClientesPrimadera.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins =  "http://localhost:3000")
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

    @GetMapping("/Addresses/{site_use_id}")
    public ResponseEntity<AddressesEntity> getAddressesBySiteId(@PathVariable Integer site_use_id){
        AddressesEntity address = addressRepository.getsite_use_id(site_use_id)
                .orElseThrow(()->new ResourceNotFoundException("La dirección por este site_use_id no existe" + site_use_id));
        return ResponseEntity.ok(address);
    }

}
