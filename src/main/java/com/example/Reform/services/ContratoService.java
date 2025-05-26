package com.example.Reform.services;

import com.example.Reform.entities.Contrato;
import com.example.Reform.repositories.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    public List<Contrato> findAll() {
        return contratoRepository.findAll();
    }

    public Optional<Contrato> findById(Long id) {
        return contratoRepository.findById(id);
    }

    public Contrato save(Contrato contrato) {
        return contratoRepository.save(contrato);
    }

    public void delete(Long id) {
        contratoRepository.deleteById(id);
    }

    public Contrato update(Long id, Contrato contratoAtualizado) {
        return contratoRepository.findById(id).map(c -> {
            c.setComprador(contratoAtualizado.getComprador());
            c.setVendedor(contratoAtualizado.getVendedor());
            c.setMaterial(contratoAtualizado.getMaterial());
            c.setValor(contratoAtualizado.getValor());
            c.setQuantidade(contratoAtualizado.getQuantidade());
            c.setStatus(contratoAtualizado.getStatus());
            return contratoRepository.save(c);
        }).orElseThrow(() -> new RuntimeException("Contrato não encontrado com id: " + id));
    }

    public List<Contrato> findByCompradorId(Long compradorId) {
        return contratoRepository.findByCompradorId(compradorId);
    }

    public List<Contrato> findByVendedorId(Long vendedorId) {
        return contratoRepository.findByVendedorId(vendedorId);
    }
}
