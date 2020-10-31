package com.presto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(mappedBy = "pedido")
    private List<Mesa> mesas;

    private String descricao;

    @ManyToMany
    @JoinTable(name = "pedido_produto",
    joinColumns = @JoinColumn(name = "pedido_id"),
    inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private List<Produto> itensDoPedido;

    public Pedido() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Mesa> getMesa() {
        return mesas;
    }

    public void setMesa(Mesa mesa) {
        this.mesas.add(mesa);
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
}
