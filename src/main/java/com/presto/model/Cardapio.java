package com.presto.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Cardapio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nome;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cardapios")
    private List<Produto> produtos;



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
}
