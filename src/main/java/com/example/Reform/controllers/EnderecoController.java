package com.example.Reform.controllers;

import com.example.Reform.entities.Endereco;
import com.example.Reform.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<Endereco>> getAllEnderecos(){
        List<Endereco> list = enderecoService.findAllEnderecos();

        if(list.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> getEnderecoById(@PathVariable Long id){

         return enderecoService.findEnderecoById(id)
                 .map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Endereco> createEndereco(@RequestBody Endereco endereco){
        Endereco enderecoCreated = enderecoService.saveEndereco(endereco);

        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoCreated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable Long id){
        enderecoService.deleteEndereco(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> updateEndereco(@PathVariable Long id, @RequestBody Endereco endereco){
        Endereco enderecoUpdate = enderecoService.updateEndereco(id, endereco);
        try {
            return ResponseEntity.ok(enderecoUpdate);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
