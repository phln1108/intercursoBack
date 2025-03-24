package com.example.Reform.controllers;

import com.example.Reform.dto.LoginRequest;
import com.example.Reform.entities.Empresa;
import com.example.Reform.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/authentication")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<?> authenticationEmpresa(@RequestBody LoginRequest loginRequest){
        Optional<Empresa> empresaFind = empresaService.findEmpresaByLogin(loginRequest.getLogin());

        if(empresaFind.isPresent()){
            Empresa empresa = empresaFind.get();

            if(passwordEncoder.matches(loginRequest.getSenha(), empresa.getSenha()))

                return ResponseEntity.ok(empresa);
            }else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("senha incorreta");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa não encontrada");
    }


}
