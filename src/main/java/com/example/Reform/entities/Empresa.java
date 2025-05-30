package com.example.Reform.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Empresa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;

    @Column(unique = true, nullable = false)
    private String cnpj;

    private String senha;
    private String telefone;
    private String descricao;

    @OneToMany(mappedBy = "empresa")
    @JsonManagedReference("empresa-material")
    private List<EmpresaMaterial> empresaMaterials;

    @OneToMany(mappedBy = "empresa")
    @JsonManagedReference("empresa-endereco")
    private List<Endereco> enderecoList;

    @OneToMany(mappedBy = "comprador")
    @JsonManagedReference("comprador-contrato")
    private List<Contrato> contratosComprador;

    @OneToMany(mappedBy = "vendedor")
    @JsonManagedReference("vendedor-contrato")
    private List<Contrato> contratosVendedor;

    public Empresa() {
    }

    public Empresa(Long id, String nome, String email, String cnpj, String telefone, String senha, String descricao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cnpj = cnpj;
        this.senha = senha;
        this.telefone = telefone;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone(){ return telefone;    }

    public void setTelefone(String telefone){this.telefone = telefone; }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Empresa empresa = (Empresa) o;
        return Objects.equals(id, empresa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public List<Endereco> getEnderecoList() {
        return enderecoList;
    }

    public List<EmpresaMaterial> getEmpresaMaterials() {
        return empresaMaterials;
    }

    public  List<Contrato> getContratosComprador() {return contratosComprador;}

    public  List<Contrato> getContratosVendedor() {return contratosVendedor;}

}
