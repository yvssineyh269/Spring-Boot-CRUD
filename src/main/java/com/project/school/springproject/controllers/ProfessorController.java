package com.project.school.springproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.project.school.springproject.entity.DTO;
import com.project.school.springproject.entity.Professor;
import com.project.school.springproject.repository.ProfessorRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/professors")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping({ "", "/" })
    public String getProfessors(Model model) {
        var professors = professorRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("professors", professors);
        return "professors/list";
    }

    @GetMapping("/new")
    public String create(Model model) {
        DTO dto = new DTO();
        model.addAttribute("dto", dto);
        return "professors/new";
    }

    @PostMapping("/new")
    public String create(
            @Valid @ModelAttribute("dto") DTO dto, BindingResult result) {
        if (professorRepository.findByMatriculate(dto.getMatriculate()) != null) {
            result.addError(new FieldError("dto", "matriculate", dto.getMatriculate(), false, null, null,
                    "Cette Matricule existe déjà"));
        }
        if (result.hasErrors()) {
            return "professors/new";
        }
        Professor professor = new Professor();
        professor.setFirstName(dto.getFirstName());
        professor.setLastName(dto.getLastName());
        professor.setMatriculate(dto.getMatriculate());
        professor.setGrade(dto.getGrade());
        professorRepository.save(professor);

        return "redirect:/professors";
    }

    // -- EDITE A PROF
    @GetMapping("/edit")
    public String editProfessor(Model model, @RequestParam int id) {
        Professor professor = professorRepository.findById(id).orElse(null);
        if (professor == null) {
            return "redirect:/professors";
        }

        DTO dto = new DTO();
        dto.setFirstName(professor.getFirstName());
        dto.setLastName(professor.getLastName());
        dto.setMatriculate(professor.getMatriculate());
        dto.setGrade(professor.getGrade());

        model.addAttribute("professor", professor);
        model.addAttribute("dto", dto);

        return "professors/edit";
    }

    @PostMapping("/edit")
    public String editProfessor(Model model, @RequestParam int id,
    @Valid @ModelAttribute DTO dto, BindingResult result) {

        Professor professor = professorRepository.findById(id).orElse(null);
        if (professor == null) {
            return "redirect:/professors";
        }

        model.addAttribute("professor", professor);
        if (result.hasErrors()) {
            return "professors/edit";
        }

        professor.setFirstName(dto.getFirstName());
        professor.setLastName(dto.getLastName());
        professor.setMatriculate(dto.getMatriculate());
        professor.setGrade(dto.getGrade());

        try {
            professorRepository.save(professor);
        } catch (Exception ex) {
            result.addError(new FieldError("dto", "matriculate", dto.getMatriculate(), false, null, null, "Cette Matricule existe déjà"));
            return "professors/edit";
        }

        return "redirect:/professors";
    }

    @GetMapping("/delete")
    public String deleteProfessor(@RequestParam int id) {
        Professor professor = professorRepository.findById(id).orElse(null);
        if (professor != null) {
            professorRepository.delete(professor);
        }
        return "redirect:/professors";
    }
}
