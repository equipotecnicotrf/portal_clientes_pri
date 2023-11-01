package com.portalClientesPrimadera.controller;

import java.util.Base64;
import java.util.List;

import com.portalClientesPrimadera.exception.ResourceNotFoundException;
import com.portalClientesPrimadera.model.UsersEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.portalClientesPrimadera.repository.UsersRepository;

@RestController
@RequestMapping("/api/v1")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/Users")
    public List<UsersEntity> ListarUsers() {
        return usersRepository.findAll();
    }

    @PostMapping("/Users")
    public UsersEntity saveUsers(@RequestBody UsersEntity users) {
        String encodepass = Base64.getEncoder().encodeToString(users.getCP_Password().getBytes());
        users.setCP_Password(encodepass);
        return usersRepository.save(users);
    }

    @GetMapping("/Users/{id}")
    public ResponseEntity<UsersEntity> listarUserPorId(@PathVariable Long id) {
        UsersEntity users = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con este ID no existe : " + id));
        return ResponseEntity.ok(users);
    }

    @PutMapping("/Users/{id}")
    public ResponseEntity<UsersEntity> actualizarUserPorId(@PathVariable Long id,
            @RequestBody UsersEntity usersRequest) {
        UsersEntity users = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con este ID no existe : " + id));
        users.setUsername(usersRequest.getUsername());
        users.setCP_name(usersRequest.getCP_name());
        users.setCP_email(usersRequest.getCP_email());
        users.setCust_account_id(usersRequest.getCust_account_id());
        users.setCust_name(usersRequest.getCust_name());
        users.setCP_rol_id(usersRequest.getCP_rol_id());
        users.setCP_estatus(usersRequest.getCP_estatus());
        users.setCP_cell_phone(usersRequest.getCP_cell_phone());
        users.setParty_id(usersRequest.getParty_id());
        users.setPayment_terms(usersRequest.getPayment_terms());
        users.setTransactional_currency_code(usersRequest.getTransactional_currency_code());
        users.setCP_type_order_id(usersRequest.getCP_type_order_id());

        UsersEntity userActualizado = usersRepository.save(users);
        return ResponseEntity.ok(userActualizado);

    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, HttpSession session) {
        UsersEntity users = usersRepository.findByUsername(username);
        String encodepass = Base64.getEncoder().encodeToString(password.getBytes());
        if (users == null || !users.getCP_Password().equals(encodepass)) {
            session.setAttribute("mensaje error", "usuario o contraseña incorrectos");
            return "usuario o contraseña incorrectos";
        } else {
            session.setAttribute("usuario", users);
            return "Loging" + " " + users.getCP_user_id();

        }
    }

    @GetMapping("/Username")
    public ResponseEntity<UsersEntity> listarUserPorusername(@RequestParam String username) {
        UsersEntity users = usersRepository.findByUsername(username);
        return ResponseEntity.ok(users);
    }

    //Actualizar contraseña
    @PutMapping("/Users/{id}/update-password")
    public ResponseEntity<UsersEntity> actualizarPassword(@PathVariable Long id,@RequestBody UsersEntity newPassword) {
        UsersEntity users = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con este ID no existe : " + id));
        String encodepass = Base64.getEncoder().encodeToString(newPassword.getCP_Password().getBytes());
        users.setCP_Password(encodepass);
        UsersEntity userActualizado = usersRepository.save(users);
        return ResponseEntity.ok(userActualizado);

    }



}
