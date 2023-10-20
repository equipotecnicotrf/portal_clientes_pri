package com.portalClientesPrimadera.controller;

import java.util.Base64;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import com.portalClientesPrimadera.model.ItemsEntity;
import com.portalClientesPrimadera.model.UsersEntity;
import com.portalClientesPrimadera.repository.ItemsRepository;






@RestController
@RequestMapping("/api/v1")

public class ItemsController {

    @Autowired
    ItemsRepository itemsRepository;

    @GetMapping("/Items")
    public List <ItemsEntity> listItems(){
        return itemsRepository.findAll();
    }
    
    @PostMapping("/Items")
    public ItemsEntity saveItems (@RequestBody ItemsEntity Items){
        return itemsRepository.save(Items);
    }

    @GetMapping("/Items/{id}")
    public ResponseEntity <ItemsEntity> getItemsByid(@PathVariable Long id){
        ItemsEntity items = itemsRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("El item no se encuentra con el id "+ id ));
        return ResponseEntity.ok(items);
    }
    @GetMapping("/Items/itemscondisponibilidad")
    public List<ItemsEntity> getAllDisponible(){
        return itemsRepository.findByDisponibilidad();
    }

    @GetMapping("/Items/itemshazpedido")
    public List<ItemsEntity> getAllHazPedido(){
        return itemsRepository.FinbyHazPedido();
    }

    
}
