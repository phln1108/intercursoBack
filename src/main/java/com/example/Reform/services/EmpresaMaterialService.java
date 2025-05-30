package com.example.Reform.services;

import com.example.Reform.entities.Empresa;
import com.example.Reform.entities.EmpresaMaterial;
import com.example.Reform.repositories.EmpresaMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaMaterialService {

    @Autowired
    private EmpresaMaterialRepository empresaMaterialRepository;

    @Autowired
    private EmpresaService empresaService;

    public List<EmpresaMaterial> findAllEmpresaMaterial() {
        return empresaMaterialRepository.findAll();
    }

    public Optional<EmpresaMaterial> findAllEmpresaMaterialById(Long id) {
        return empresaMaterialRepository.findById(id);
    }

    public EmpresaMaterial saveEmpresaMaterial(EmpresaMaterial empresaMaterial) {
        System.out.println("RECEBIDO:");
        System.out.println("Nome: " + empresaMaterial.getNome());
        System.out.println("Descrição: " + empresaMaterial.getDescricao());
        System.out.println("Categoria: " + empresaMaterial.getCategoria());
        System.out.println("Empresa ID: " + empresaMaterial.getEmpresa().getId());
        return empresaMaterialRepository.save(empresaMaterial);
    }

    public EmpresaMaterial updateEmpresaMaterial(Long id, EmpresaMaterial empresaMaterial) {
        Optional<EmpresaMaterial> empresaMaterialFind = empresaMaterialRepository.findById(id);

        if (!empresaMaterialFind.isPresent()) {
            throw new RuntimeException("EmpresaMaterial não encontrada com ID:" + id);
        }
        EmpresaMaterial empresaMaterialUpdate = empresaMaterialFind.get();

        empresaMaterialUpdate.setCategoria(empresaMaterial.getCategoria());
        empresaMaterialUpdate.setDescricao(empresaMaterial.getDescricao());
        empresaMaterialUpdate.setNome(empresaMaterial.getNome());
        empresaMaterialUpdate.setQuantidade(empresaMaterial.getQuantidade());
        empresaMaterialUpdate.setValor(empresaMaterial.getValor());
        return empresaMaterialRepository.save(empresaMaterialUpdate);
    }

    public List<EmpresaMaterial> findMaterialsByEmpresaId(Long id) {
        Empresa empresa = empresaService.findEmpresaById(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        return empresaMaterialRepository.findByEmpresa(empresa);
    }

    public void deleteEmpresaMaterialById(Long id) {
        empresaMaterialRepository.deleteById(id);
    }
}
