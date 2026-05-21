
package com.example.aulabd.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Aluno {

    private String id, nome, cpf, password;

    //Constrtuor para a pagina do formulario
    public Aluno(){
        
    }

    //Construtor bom para Select
    public Aluno(String cpf, String id, String nome, String password) {
        this.cpf = cpf;
        this.id = id;
        this.nome = nome;
        this.password = password;
    }

    //Construtor bom para insert
    public Aluno(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPassword(){
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setPassword(String senha) {
        this.password = senha;
    }

    public static Aluno converter(Map<String,Object> registro){
        String nome = (String) registro.get("nome");
        UUID id = (UUID) registro.get("id");
        String cpf = (String) registro.get("cpf");
        String password = (String) registro.get("password");
        return new Aluno(cpf,id.toString(),nome,password);
    }

    public static ArrayList<Aluno> converterTodos(List<Map<String,Object>> registros){
        ArrayList<Aluno> aux = new ArrayList<>();
        for(Map<String,Object> registro : registros){
            aux.add(converter(registro));
        }
        return aux;
    }

}
