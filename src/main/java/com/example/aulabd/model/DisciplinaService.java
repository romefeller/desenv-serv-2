package com.example.aulabd.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaDAO disciplinaDAO;

    public void inserirDisciplina(Disciplina disciplina){
        disciplinaDAO.inserirDisciplina(disciplina);
    }

    public ArrayList<Disciplina> listarDisciplinas(){
        return disciplinaDAO.listarDisciplinas();
    }

    public void atribuirProfessor(String disciplinaId, String professorId){
        disciplinaDAO.atribuirProfessor(disciplinaId, professorId);
    }

}
