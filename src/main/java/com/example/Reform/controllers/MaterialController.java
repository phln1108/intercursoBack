package com.example.Reform.controllers;

import com.example.Reform.entities.Material;
import com.example.Reform.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/materiais")
@CrossOrigin(origins = "http://localhost:3000")
public class MaterialController {

    @Autowired
     private MaterialService materialService;

    @GetMapping
    public ResponseEntity<List<Material>> getAllMaterials(){
        List<Material> materialList = materialService.findAllMaterials();

        if(materialList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(materialList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Material> getMaterialById(@PathVariable Long id){
        return materialService.findMaterialById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Material> createMaterial(@RequestBody Material material){
        Material materialCreated = materialService.saveMaterial(material);

        return ResponseEntity.status(HttpStatus.CREATED).body(materialCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Material> updateMaterial( @PathVariable Long id, @RequestBody Material material){
        try{
            Material materialUpdate = materialService.updateMaterial(id, material);
            return ResponseEntity.ok(materialUpdate);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Long id){
        materialService.deleteMaterialById(id);
        return ResponseEntity.noContent().build();
    }
}
