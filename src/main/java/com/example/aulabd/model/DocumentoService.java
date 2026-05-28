package com.example.aulabd.model;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository repo;

    public Documento salvar(Documento d) {
        return repo.save(d);
    }

    public List<Documento> listar() {
        return repo.findAll();
    }

    public Documento buscar(UUID id) {
        return repo.findById(id).orElse(null);
    }

    public void deletar(UUID id) {
        repo.deleteById(id);
    }
}
