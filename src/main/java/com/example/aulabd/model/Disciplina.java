
package com.example.aulabd.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Disciplina {
    private String id, nome, sigla, professorId, professorNome;

    public Disciplina(){

    }

    public Disciplina(String sigla, String id, String nome) {
        this.sigla = sigla;
        this.id = id;
        this.nome = nome;
    }

    public Disciplina(String sigla, String nome) {
        this.sigla = sigla;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }

    public String getProfessorId() {
        return professorId;
    }

    public String getProfessorNome() {
        return professorNome;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    public void setProfessorNome(String professorNome) {
        this.professorNome = professorNome;
    }

    public static Disciplina converter(Map<String,Object> registro){
        String nome = (String) registro.get("nome");
        UUID id = (UUID) registro.get("id");
        String sigla = (String) registro.get("sigla");
        Disciplina d = new Disciplina(sigla, id.toString(), nome);
        Object pid = registro.get("professor_id");
        if (pid != null) {
            d.setProfessorId(pid.toString());
        }
        Object pnome = registro.get("professor_nome");
        if (pnome != null) {
            d.setProfessorNome(pnome.toString());
        }
        return d;
    }

    public static ArrayList<Disciplina> converterTodos(List<Map<String,Object>> registros){
        ArrayList<Disciplina> aux = new ArrayList<>();
        for(Map<String,Object> registro : registros){
            aux.add(converter(registro));
        }
        return aux;
    }

}
