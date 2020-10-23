package com.presto.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private  String nome;

    @ManyToMany
    @JoinTable(name = "mesa_pedido",
    joinColumns = @JoinColumn(name = "mesa_id"),
    inverseJoinColumns = @JoinColumn(name = "pedido_id"))
    private List<Pedido> pedido;


    public Mesa() {
    }

    public Mesa(String nome, Pedido pedido) {
        this.nome = nome;
        this.pedido = (List<Pedido>) pedido;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Pedido> getPedido() {
        return this.pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido.add(pedido);
    }


}
