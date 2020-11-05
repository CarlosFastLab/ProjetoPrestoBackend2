package com.presto.model;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;

    private String descricao;

    private Double valorTotal;

    private long maiorTempo;

    @ManyToMany
    @JoinTable(name = "pedido_produto",
    joinColumns = @JoinColumn(name = "pedido_id"),
    inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private List<Produto> itensDoPedido;

    public Pedido() {
    }

    public Pedido(long id, Mesa mesa, String descricao, Double valorTotal) {
        this.id = id;
        this.mesa = mesa;
        this.descricao = descricao;
        this.valorTotal = valorTotal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public List<Produto> getItensDoPedido() {
        return itensDoPedido;
    }

    public void setItensDoPedido(Produto itensDoPedido) {
        this.itensDoPedido.add(itensDoPedido);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public long getMaiorTempo() {
        return maiorTempo;
    }

    public void setMaiorTempo(long maiorTempo) {
        this.maiorTempo = maiorTempo;
    }
}
