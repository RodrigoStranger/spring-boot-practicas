package com.ebiz.estudiantescmd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @GetMapping("/prueba")
    public String prueba(){
        return "prueba de la API Estudiante";
    }
}
