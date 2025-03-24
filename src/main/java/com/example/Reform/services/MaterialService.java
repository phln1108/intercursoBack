package com.example.Reform.services;

import com.example.Reform.entities.Material;
import com.example.Reform.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {
    @Autowired
    private MaterialRepository materialRepository;

    public List<Material> findAllMaterials(){
        return materialRepository.findAll();
    }

    public Optional<Material> findMaterialById(Long id){
        return materialRepository.findById(id);
    }

    public Material saveMaterial(Material material){
        return materialRepository.save(material);
    }
    public Material updateMaterial(Long id, Material material){
        Optional<Material> materialFind = materialRepository.findById(id);

        if(materialFind.isPresent()){
            Material updateMaterial = materialFind.get();

            updateMaterial.setTipo(material.getTipo());

            return materialRepository.save(updateMaterial);
        } else {
            throw new RuntimeException("Material não encontrado com ID:"+id);
        }
    }

    public void deleteMaterialById(Long id){
        materialRepository.deleteById(id);
    }
}
