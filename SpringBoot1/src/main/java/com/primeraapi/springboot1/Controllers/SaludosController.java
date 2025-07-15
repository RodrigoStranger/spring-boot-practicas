package com.primeraapi.springboot1.Controllers;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apisaludos")
public class SaludosController {

    @GetMapping("/saludoApi/{nombre}")
    public String saludo(@PathVariable String nombre) {
        return "Hola " + nombre;
    }

    @GetMapping("")
    public String obtenerUsuarioPorId(@RequestParam("id") int id) {
        return "Saludo con ID: " + id;
    }

}
