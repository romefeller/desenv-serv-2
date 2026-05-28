package com.example.aulabd.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.aulabd.model.Documento;
import com.example.aulabd.model.DocumentoService;
import com.example.aulabd.model.Professor;
import com.example.aulabd.model.ProfessorService;

@Controller
public class DocumentoController {

    private static final String UPLOAD_DIR = "./uploads";

    @Autowired
    private DocumentoService documentoService;

    @Autowired
    private ProfessorService professorService;

    @GetMapping("/document")
    public String listar(Model model) {
        List<Documento> documentos = documentoService.listar();
        model.addAttribute("documentos", documentos);
        return "listardocumento";
    }

    @GetMapping("/document/upload")
    public String formUpload(Model model) {
        List<Professor> professores = professorService.listar();
        model.addAttribute("professores", professores);
        return "formuploaddocumento";
    }

    @PostMapping("/document/upload")
    public String upload(@RequestParam("file") MultipartFile file,
                         @RequestParam("professorId") UUID professorId) throws IOException {
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String nomeOriginal = file.getOriginalFilename();
        String nomeArquivo = UUID.randomUUID() + "_" + nomeOriginal;
        Path destino = uploadPath.resolve(nomeArquivo);
        Files.copy(file.getInputStream(), destino);

        Professor professor = professorService.buscar(professorId);
        Documento doc = new Documento(nomeOriginal, destino.toString(), professor);
        documentoService.salvar(doc);

        return "redirect:/document";
    }

    @GetMapping("/document/{id}")
    public ResponseEntity<Resource> serve(@PathVariable UUID id) throws IOException {
        Documento doc = documentoService.buscar(id);
        if (doc == null) {
            return ResponseEntity.notFound().build();
        }

        Path path = Paths.get(doc.getCaminho());
        Resource resource = new FileSystemResource(path);
        String contentType = Files.probeContentType(path);
        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}
