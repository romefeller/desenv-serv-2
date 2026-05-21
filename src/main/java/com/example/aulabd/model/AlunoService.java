package com.example.aulabd.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    AlunoDAO alunoDAO;

    @Autowired
    MatriculaDAO matriculaDAO;

    public void inserirAluno(Aluno aluno){
        alunoDAO.inserirAluno(aluno);
    }

    public Aluno mostrarAluno(String uuid){
        return alunoDAO.mostrarAluno(uuid);
    }

    public ArrayList<Aluno> listarAlunos(){
        return alunoDAO.listarAlunos();
    }

    public void atualizarAluno(Aluno novo, String uuid){
        alunoDAO.atualizarAluno(novo, uuid);
    }

    public void deletarAluno(String uuid){
        alunoDAO.deletarAluno(uuid);        
    }

    public ArrayList<Disciplina> listarMatriculadas(String uuidAluno){
        return alunoDAO.listarMatriculadas(uuidAluno);
    }

    public void matricular(Matricula mat){
        matriculaDAO.matricular(mat);
    }

    public void inserirPerfil(String uuid){
        alunoDAO.inserirPerfil(uuid);
    }

    public String obterUUID(String cpf){
        return alunoDAO.obterUUID(cpf);
    }

}
