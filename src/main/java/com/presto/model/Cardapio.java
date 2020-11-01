package com.presto.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cardapio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nome;

    @OneToMany
    @JoinTable(name = "cardapio_produto",
            joinColumns = @JoinColumn(name = "cardapio_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private List<Produto> produtos = new ArrayList<>();



    public Cardapio(){}

    public Cardapio(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public long getId() {
        return id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setProdutos(Produto produto) {
        this.produtos.add(produto);
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
