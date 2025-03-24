package com.example.Reform.repositories;

import com.example.Reform.entities.EmpresaMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmpresaMaterialRepository extends JpaRepository<EmpresaMaterial, Long> {
}
