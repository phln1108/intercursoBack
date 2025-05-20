package com.example.Reform.entities;

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
    private String logradouro;
    private String numero;
    private String complemento;
    private String cep;
    private String bairro;
    private String municipio;
    private String uf;

    @OneToMany(mappedBy = "empresa")
    private List<EmpresaMaterial> empresaMaterials;

    @OneToMany(mappedBy = "empresa")
    private List<Endereco> enderecoList;


    public Empresa() {
    }

    public Empresa(Long id, String nome, String email, String cnpj, String telefone, String senha, String logradouro, String numero, String complemento, String cep, String bairro, String municipio, String uf) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cnpj = cnpj;
        this.senha = senha;
        this.telefone = telefone;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.bairro = bairro;
        this.municipio = municipio;
        this.uf = uf;
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

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public List<Endereco> getEnderecoList() {
        return enderecoList;
    }


    public List<EmpresaMaterial> getEmpresaMaterials() {
        return empresaMaterials;
    }

}
