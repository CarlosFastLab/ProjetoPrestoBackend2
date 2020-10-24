package com.presto.model;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class ProdutoRetorno implements Serializable {
    private long id;
    private String nome;
    private String descricao;
    private Cardapio cardapios;
    private Date tempo;
    private File imagem;
    private double valor;
    private List<Pedido> pedido;

    public ProdutoRetorno(long id, String nome, String descricao, Cardapio cardapios, Date tempo, File imagem, double valor, List<Pedido> pedido) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.cardapios = cardapios;
        this.tempo = tempo;
        this.imagem = imagem;
        this.valor = valor;
        this.pedido = pedido;
    }

    public ProdutoRetorno() {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Cardapio getCardapios() {
        return cardapios;
    }

    public void setCardapios(Cardapio cardapios) {
        this.cardapios = cardapios;
    }

    public Date getTempo() {
        return tempo;
    }

    public void setTempo(Date tempo) {
        this.tempo = tempo;
    }

    public File getImagem() {
        return imagem;
    }

    public void setImagem(File imagem) {
        this.imagem = imagem;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public List<Pedido> getPedido() {
        return pedido;
    }

    public void setPedido(List<Pedido> pedido) {
        this.pedido = pedido;
    }
}
