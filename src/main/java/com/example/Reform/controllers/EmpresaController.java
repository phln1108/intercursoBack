package com.example.Reform.controllers;

import com.example.Reform.entities.Empresa;
import com.example.Reform.repositories.EmpresaRepository;
import com.example.Reform.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/empresas")
@CrossOrigin(origins = "http://localhost:5173")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<List<Empresa>> getAllEmpresas(){
        List<Empresa> empresaList = empresaService.findAllEmpresas();

        if(empresaList.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(empresaList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable Long id){
        return empresaService.findEmpresaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Empresa> createEmpresa(@RequestBody Empresa empresa){
        Empresa empresaCreated = empresaService.saveEmpresa(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> updateEmpresa(@RequestBody Empresa empresa, @PathVariable Long id){
        try{
            Empresa updateEmpresa = empresaService.updateEmpresa(id, empresa);
            return ResponseEntity.ok(updateEmpresa);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable Long id){
        empresaService.deleteEmpresaById(id);
        return ResponseEntity.noContent().build();
    }


}
