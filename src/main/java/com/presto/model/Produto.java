package com.presto.model;


import com.presto.util.DataUtil;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToMany
    private List<Cardapio> cardapios;
    private String nome;
    private String tipo;
    private String descricao;
    private Date tempo;
    private String imagem;
    private double valor;

    public Produto(){}

    public Produto(String nome, String tipo, String tempo,  String descricao, String imagem){
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

    public List<Cardapio> getCardapios() {
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

    public void setTempo(String tempo) {
        this.tempo =  DataUtil.convertToDate(tempo);
    }

    public void setCardapios(Cardapio cardapio) {
        this.cardapios.add(cardapio);
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