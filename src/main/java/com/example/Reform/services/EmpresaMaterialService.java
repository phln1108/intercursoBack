package com.example.Reform.services;

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

    public List<EmpresaMaterial> findAllEmpresaMaterial(){
        return empresaMaterialRepository.findAll();
    }

    public Optional<EmpresaMaterial> findAllEmpresaMaterialById(Long id){
        return empresaMaterialRepository.findById(id);
    }

    public EmpresaMaterial saveEmpresaMaterial(EmpresaMaterial empresaMaterial){
        return empresaMaterialRepository.save(empresaMaterial);
    }

    public EmpresaMaterial updateEmpresaMaterial(Long id, EmpresaMaterial empresaMaterial){
        Optional<EmpresaMaterial> empresaMaterialFind = empresaMaterialRepository.findById(id);

        if(empresaMaterialFind.isPresent()){
            EmpresaMaterial empresaMaterialUpdate = empresaMaterialFind.get();
            empresaMaterialUpdate.setQuantidade(empresaMaterial.getQuantidade());
            return empresaMaterialRepository.save(empresaMaterialUpdate);
        }else {
            throw new RuntimeException("EmpresaMaterial não encontrada com ID:"+id);
        }
    }

    public void deleteEmpresaMaterialById(Long id){
        empresaMaterialRepository.deleteById(id);
    }
}
