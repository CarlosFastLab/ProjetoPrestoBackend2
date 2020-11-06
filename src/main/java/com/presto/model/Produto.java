package com.presto.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.presto.util.DataUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Produto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Cardapio cardapios;

    @JsonIgnore
    @ManyToMany(mappedBy = "itensDoPedido")
    private List<Pedido> pedido;
    private String nome;
    private String tipo;
    private String descricao;
    @JsonFormat(pattern = "HH:mm:ss")
    private Integer tempo;
    private String imagem;
    
    private double valor;

    public Produto(){}

    public Produto(String nome, String tipo, Integer tempo,  String descricao, String imagem){
        this.nome = nome;
        this.tipo = tipo;
        this.tempo = tempo;
        this.descricao = descricao;
        this.imagem = imagem;
    }

    public List<Pedido> getPedido() {
        return this.pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido.add(pedido);
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getTempo() {
        return tempo;
    }

    public Cardapio getCardapios() {
        return cardapios;
    }

    public double getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getImagem() {
        return imagem;
    }



    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTempo(Integer tempo) {
        this.tempo = tempo;
    }



    public void setCardapios(Cardapio cardapio) {
        this.cardapios =cardapio;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
