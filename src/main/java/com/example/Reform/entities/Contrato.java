package com.example.Reform.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Entity
public class Contrato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "comprador_id", nullable = false)
    @JsonBackReference("comprador-contrato")
    private Empresa comprador;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    @JsonBackReference("vendedor-contrato")
    private Empresa vendedor;

    @ManyToOne
    @JoinColumn(name = "esmpresa_material_id", nullable = false)
    private EmpresaMaterial material;

    private double valor;

    private double quantidade;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_inicio", nullable = false, updatable = false)
    private Date dataInicio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContractStates status;

    public Contrato() {
        this.dataInicio = new Date(); // atribui automaticamente a data atual
        this.status = ContractStates.PENDENTE; // status inicial padrão
    }

    public Contrato(Long id, Empresa comprador, Empresa vendedor, EmpresaMaterial material, double valor, double quantidade) {
        this.id = id;
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.material = material;
        this.valor = valor;
        this.quantidade = quantidade;
        this.dataInicio = new Date();
        this.status = ContractStates.PENDENTE;
    }

    @PrePersist
    protected void onCreate() {
        this.dataInicio = new Date();
        if (this.status == null) {
            this.status = ContractStates.PENDENTE;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Contrato contrato = (Contrato) o;
        return Objects.equals(id, contrato.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Long getId() {
        return id;
    }

    public Empresa getComprador() {
        return comprador;
    }

    public void setComprador(Empresa comprador) {
        this.comprador = comprador;
    }

    public Empresa getVendedor() {
        return vendedor;
    }

    public void setVendedor(Empresa vendedor) {
        this.vendedor = vendedor;
    }

    public EmpresaMaterial getMaterial() {
        return material;
    }

    public void setMaterial(EmpresaMaterial material) {
        this.material = material;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    // Evite setter para dataInicio se quiser manter controle automático, mas se necessário:
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public ContractStates getStatus() {
        return status;
    }

    public void setStatus(ContractStates status) {
        this.status = status;
    }

}
