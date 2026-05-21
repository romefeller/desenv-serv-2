

package com.example.aulabd.model;

import java.time.LocalDate;

public class Matricula {

    private String alunoId, disciplinaId; 
    private LocalDate dataMatricula;

    public Matricula(){}

    public Matricula(String alunoId, String disciplinaId) {
        this.alunoId = alunoId;
        this.dataMatricula = LocalDate.now();
        this.disciplinaId = disciplinaId;
    }

    public String getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(String alunoId) {
        this.alunoId = alunoId;
    }

    public String getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(String disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }





}
