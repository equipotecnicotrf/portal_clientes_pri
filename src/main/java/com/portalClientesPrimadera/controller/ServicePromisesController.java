package com.portalClientesPrimadera.controller;

import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import com.portalClientesPrimadera.model.ServicePromisesEntity;
import com.portalClientesPrimadera.repository.ServicePromisesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ServicePromisesController {

    @Autowired
    ServicePromisesRepository servicePromisesRepository;

    @GetMapping("/ServicePromises")
    public List<ServicePromisesEntity> listarPromesasDeServicio(){
        return servicePromisesRepository.findAll();
    }

    @PostMapping("/ServicePromises")
    public ServicePromisesEntity saveServicePromise (@RequestBody ServicePromisesEntity ServicePromise){
        return servicePromisesRepository.save(ServicePromise);
    }

    @GetMapping ("/ServicePromises/{id}")
    public ResponseEntity<ServicePromisesEntity> GetServicePromiseById(@PathVariable Long id){
        ServicePromisesEntity ServicePromise = servicePromisesRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("La promesa de servicio no se encuentra con el id " + id));
        return ResponseEntity.ok(ServicePromise);
    }


}
