package com.example.aulabd.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.aulabd.model.Disciplina;
import com.example.aulabd.model.DisciplinaService;
import com.example.aulabd.model.Professor;
import com.example.aulabd.model.ProfessorService;

@Controller
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping("/professor/cadastrar")
    public String formProfessor(Model model) {
        model.addAttribute("professor", new Professor());
        return "formprofessor";
    }

    @PostMapping("/professor/cadastrar")
    public String postProfessor(@ModelAttribute Professor professor) {
        professorService.inserir(professor);
        return "redirect:/professor/listar";
    }

    @GetMapping("/professor/listar")
    public String listar(Model model) {
        List<Professor> professores = professorService.listar();
        model.addAttribute("professores", professores);
        return "listarprofessor";
    }

    @PostMapping("/professor/{id}/deletar")
    public String deletar(@PathVariable("id") UUID id) {
        professorService.deletar(id);
        return "redirect:/professor/listar";
    }

    @GetMapping("/professor/atribuir")
    public String atribuirForm(Model model) {
        List<Professor> professores = professorService.listar();
        List<Disciplina> disciplinas = disciplinaService.listarDisciplinas();
        model.addAttribute("professores", professores);
        model.addAttribute("disciplinas", disciplinas);
        return "atribuirprofessor";
    }

    @PostMapping("/professor/atribuir")
    public String atribuirPost(@RequestParam("professorId") String professorId,
                               @RequestParam("disciplinaId") String disciplinaId) {
        disciplinaService.atribuirProfessor(disciplinaId, professorId);
        return "redirect:/disciplina/listar";
    }
}
