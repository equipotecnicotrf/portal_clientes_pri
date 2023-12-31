package com.portalClientesPrimadera.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.portalClientesPrimadera.model.IVAEntity;
import com.portalClientesPrimadera.repository.IVARepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1")
public class IVAController {
    @Autowired
    IVARepository ivaRepository;

    @GetMapping ("/IVA")
    public List <IVAEntity> listarPrices(){
        return ivaRepository.findAll();
    }

    @PostMapping ("/IVA")

    public IVAEntity savePrice (@RequestBody IVAEntity IVA){
        return ivaRepository.save(IVA);
    }

    @GetMapping ("/IVA/{id}") 
    public ResponseEntity <IVAEntity> GetPricesById(@PathVariable Long id){
        IVAEntity IVA = ivaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("El IVA no se encuentra con el id " + id));
        return ResponseEntity.ok(IVA); 
    }

    @PutMapping("/IVA/{id}")
    public ResponseEntity<IVAEntity> actualizarIvaPorId(@PathVariable Long id,
            @RequestBody IVAEntity ivaRequest) {
        IVAEntity IVA = ivaRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("El iva con este ID no existe : " + id));
        IVA.setCP_IVA(ivaRequest.getCP_IVA());
        IVA.setCP_IVA_date_start(ivaRequest.getCP_IVA_date_start());
        IVA.setCP_IVA_date_end(ivaRequest.getCP_IVA_date_end());
        IVAEntity IvaActualizada = ivaRepository.save(IVA);
        return ResponseEntity.ok(IvaActualizada);
    }

}
