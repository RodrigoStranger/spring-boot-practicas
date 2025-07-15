package com.primeraapi.springboot1.Controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/figuras")
public class FigurasController {

    @GetMapping("/triangulo")
    public String triangulo() {
        return "Triangulo";
    }
}
