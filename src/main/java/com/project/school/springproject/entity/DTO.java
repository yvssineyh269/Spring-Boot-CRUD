package com.project.school.springproject.entity;

import jakarta.validation.constraints.*;

public class DTO {
    @NotEmpty(message = "Le prénom est obligatoire")
    private String firstName;
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @NotEmpty(message = "Le nom de famille est obligatoire")
    private String lastName;
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @NotEmpty(message = "Le Matricule est obligatoire")
    private String matriculate;
    public String getMatriculate() {
        return matriculate;
    }
    public void setMatriculate(String matriculate) {
        this.matriculate = matriculate;
    }
    @NotEmpty(message = "Le grade est requis")
    private String grade; // Professeur des universités, Maître de conférences,Professeur invité, Professeur associé, Professeur émérite.   
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
}
