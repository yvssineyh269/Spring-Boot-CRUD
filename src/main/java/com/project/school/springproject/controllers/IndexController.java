package com.project.school.springproject.controllers;

import com.project.school.springproject.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @Autowired
    private ProfessorRepository professorRepository;
    @GetMapping("/")
    public String index(Model model) {
        var professors = professorRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("professors", professors);
        return "index";
    }

}
