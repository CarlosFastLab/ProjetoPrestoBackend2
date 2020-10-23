package com.presto.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Imagem implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private long id;

    @Lob
    byte[] bytes;

    @OneToOne
    private Produto produto;

    public Imagem() {

    }

    public Imagem(byte[] bytes, Produto produto) {
        this.bytes = bytes;
        this.produto = produto;
    }

    public long getId() {
        return id;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
