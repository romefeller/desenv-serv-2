package com.example.aulabd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.aulabd.model.Professor;
import com.example.aulabd.model.ProfessorService;

@RestController
@CrossOrigin(origins = "*")
public class ProfessorInsertController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping("/professor-insert")
    public Professor inserir(@RequestBody Professor professor) {
        return professorService.inserir(professor);
    }
}
