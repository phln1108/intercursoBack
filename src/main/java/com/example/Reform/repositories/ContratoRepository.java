package com.example.Reform.repositories;

import com.example.Reform.entities.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    List<Contrato> findByCompradorId(Long compradorId);

    List<Contrato> findByVendedorId(Long vendedorId);
}

