package com.project.school.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.school.springproject.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer>{
    public Professor findByMatriculate(String matriculate);

}
