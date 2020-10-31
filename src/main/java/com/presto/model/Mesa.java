package com.presto.model;


import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Mesa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private  String nome;


    @OneToOne(mappedBy = "mesa")
    private Pedido pedido;


    public Mesa() {
    }

    public Mesa(String nome, Pedido pedido) {
        this.nome = nome;
        this.pedido =  pedido;
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
