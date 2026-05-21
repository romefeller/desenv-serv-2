package com.example.aulabd.model;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repo;

    public Professor inserir(Professor p) {
        return repo.save(p);
    }

    public List<Professor> listar() {
        return repo.findAll();
    }

    public Professor buscar(UUID id) {
        return repo.findById(id).orElse(null);
    }

    public void deletar(UUID id) {
        repo.deleteById(id);
    }
}
