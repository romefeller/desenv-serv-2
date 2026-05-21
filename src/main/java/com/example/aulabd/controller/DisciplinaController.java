
package com.example.aulabd.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.aulabd.model.Disciplina;
import com.example.aulabd.model.DisciplinaService;

@Controller
public class DisciplinaController {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/disciplina/cadastrar")
	public String formDisciplina(Model model) {
		model.addAttribute("disciplina",new Disciplina());
		return "formdisciplina";
	}
	
	@PostMapping("/disciplina/cadastrar")
	public String postDisciplina(@ModelAttribute Disciplina disciplina,
			                  Model model) {
        //AlunoService eh feito via autowired
		DisciplinaService cs = context.getBean(DisciplinaService.class);
		cs.inserirDisciplina(disciplina);
		return "sucesso";
	}

	@GetMapping("/disciplina/listar")
	public String listarDisciplinas(Model model){
		DisciplinaService cs = context.getBean(DisciplinaService.class);
		ArrayList<Disciplina> disciplinas = (ArrayList<Disciplina>) cs.listarDisciplinas();
		model.addAttribute("disciplinas",disciplinas);
		return "listardisciplina";
	}

	
}
