package com.example.Reform.controllers;

import com.example.Reform.entities.Contrato;
import com.example.Reform.services.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contratos")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    @GetMapping
    public ResponseEntity<List<Contrato>> getAll() {
        return ResponseEntity.ok(contratoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrato> getById(@PathVariable Long id) {
        return contratoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Contrato> create(@RequestBody Contrato contrato) {
        return ResponseEntity.ok(contratoService.save(contrato));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contrato> update(@PathVariable Long id, @RequestBody Contrato contrato) {
        try {
            return ResponseEntity.ok(contratoService.update(id, contrato));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contratoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/comprador/{empresaId}")
    public ResponseEntity<List<Contrato>> getByComprador(@PathVariable Long empresaId) {
        List<Contrato> contratos = contratoService.findByCompradorId(empresaId);
        return ResponseEntity.ok(contratos);
    }

    @GetMapping("/vendedor/{empresaId}")
    public ResponseEntity<List<Contrato>> getByVendedor(@PathVariable Long empresaId) {
        List<Contrato> contratos = contratoService.findByVendedorId(empresaId);
        return ResponseEntity.ok(contratos);
    }
}
