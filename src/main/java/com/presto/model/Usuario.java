package com.presto.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;





@Entity
public class Usuario{

    
    private String nome;
    
    private String email;
    
    private String senha;
    
    private String dataNascimento;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    
    public Usuario(){}

    public Usuario(long id){
      this.id = id;
    }
    
    public Usuario(String nome, String email, String senha, String dataDeNascimento){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataDeNascimento;
    }

    public String getNome(){
      return this.nome;
    }
    public String getEmail(){
      return this.email;
    }
    public String getSenha(){
      return this.senha;
    }
    public String getDataNascimento(){
      return this.dataNascimento;
    }

    public Long getId(){
      return this.id;
    }
    public void setNome(String nome){
      this.nome = nome;
    }
    public void setEmail(String email){
      this.email = email;
    }
    public void setSenha(String senha){
       this.senha = senha;
    }
    public void setDataNascimento(String dataDeNascimento){
       this.dataNascimento = dataDeNascimento;
    }

}