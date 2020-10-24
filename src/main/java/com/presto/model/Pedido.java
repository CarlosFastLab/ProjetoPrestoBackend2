package com.presto.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToMany(mappedBy = "pedido")
    private List<Mesa> mesas;

    @ManyToMany
    @JoinTable(name = "pedido_produto",
    joinColumns = @JoinColumn(name = "pedido_id"),
    inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private List<Produto> itensDoPedido;


    public Pedido() {
    }

    public Pedido(List<Mesa> mesa, List<Produto> itensDoPedido) {
        this.mesas = mesa;
        this.itensDoPedido = itensDoPedido;
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
}
