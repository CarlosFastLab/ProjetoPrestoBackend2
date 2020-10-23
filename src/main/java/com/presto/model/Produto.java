package com.presto.model;


import com.presto.util.DataUtil;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Cardapio cardapios;
    private String nome;
    private String tipo;
    private String descricao;
    private Date tempo;

    @Lob
    private byte[] imagem;

    private double valor;

    public Produto(){}

    public Produto(String nome, String tipo, String tempo, String descricao, byte[] imagem){
        this.nome = nome;
        this.tipo = tipo;

        this.tempo = new DataUtil().convertToDate(tempo);
        this.descricao = descricao;
        this.imagem = imagem;
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

    public Date getTempo() {
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

    public byte[] getImagem() {
        return imagem;
    }




    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTempo(String tempo) {
        this.tempo =  DataUtil.convertToDate(tempo);
    }

    public void setCardapios(Cardapio cardapio) {
        this.cardapios = cardapio;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
