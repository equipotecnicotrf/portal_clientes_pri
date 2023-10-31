package com.portalClientesPrimadera.controller;

import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import com.portalClientesPrimadera.model.NotificationsEntity;
import com.portalClientesPrimadera.model.ServicePromisesEntity;
import com.portalClientesPrimadera.model.UsersEntity;
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

    @PutMapping ("/ServicePromises/{id}")
    public ResponseEntity<ServicePromisesEntity> actualizarServicePromiseById(@PathVariable Long id, @RequestBody ServicePromisesEntity PromiseRequest){
        ServicePromisesEntity ServicePromise = servicePromisesRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("La promesa de servicio no se encuentra con el id " + id));
                ServicePromise.setCP_type_promise(PromiseRequest.getCP_type_promise());
                ServicePromise.setCP_description_promise(PromiseRequest.getCP_description_promise());
        ServicePromisesEntity PromiseActualizada = servicePromisesRepository.save(ServicePromise);
        return ResponseEntity.ok(PromiseActualizada);
    }

    @GetMapping("/ServicePromises/context")
    public List<ServicePromisesEntity> getpromesaportipo(@RequestParam(name = "CP_type_promise") String CP_type_promise) {
        return servicePromisesRepository.findByCPtypepromise(CP_type_promise);
    }


}
