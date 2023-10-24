package com.portalClientesPrimadera.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import com.portalClientesPrimadera.model.ConsecutiveEntity;
import com.portalClientesPrimadera.model.PricesEntity;
import com.portalClientesPrimadera.repository.ConsecutiveRepository;

@RestController
@RequestMapping("/api/v1")
public class ConsecutiveController {
    @Autowired
    private ConsecutiveRepository consecutiveRepository;

     @GetMapping("/Consecutive")
    public List<ConsecutiveEntity> ListarConsecutivos() {
        return consecutiveRepository.findAll();
    }

    @PostMapping ("/Consecutive")
    public ConsecutiveEntity saveConsecutive (@RequestBody ConsecutiveEntity consecutive){
        return consecutiveRepository.save(consecutive);
    
    }

    @GetMapping ("/Consecutive/{id}")
    public ResponseEntity <ConsecutiveEntity> GetConsecutiveById(@PathVariable Long id){
        ConsecutiveEntity Consecutives = consecutiveRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("El consecutivo no se encuentra con el id " + id));
        return ResponseEntity.ok(Consecutives);
    }

    @PutMapping("/Consecutive/{id}")
    public ResponseEntity<ConsecutiveEntity> actualizarConsecutive(@PathVariable Long id,
            @RequestBody ConsecutiveEntity ConsecutiveRequest) {
        ConsecutiveEntity Consecutive = consecutiveRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("El usuario con este ID no existe : " + id));
        Consecutive.setCP_Consecutive_code(ConsecutiveRequest.getCP_Consecutive_code());
        Consecutive.setCP_Consecutive_num(ConsecutiveRequest.getCP_Consecutive_num());
        Consecutive.setCP_Consecutive_date_start(ConsecutiveRequest.getCP_Consecutive_date_start());
        Consecutive.setCP_Consecutive_date_end(ConsecutiveRequest.getCP_Consecutive_date_end());

        ConsecutiveEntity ConscutivoActualizado = consecutiveRepository.save(Consecutive);
        return ResponseEntity.ok(ConscutivoActualizado);

}
}
