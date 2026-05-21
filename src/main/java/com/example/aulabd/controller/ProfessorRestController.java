package com.example.aulabd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aulabd.model.Professor;
import com.example.aulabd.model.ProfessorService;

@RestController
@RequestMapping("/rest")
public class ProfessorRestController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping("/lista-prof")
    public List<Professor> listaProfessores() {
        return professorService.listar();
    }
}
