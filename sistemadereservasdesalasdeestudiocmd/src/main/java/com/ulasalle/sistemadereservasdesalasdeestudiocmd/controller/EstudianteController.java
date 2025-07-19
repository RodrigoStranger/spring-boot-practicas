package com.ulasalle.sistemadereservasdesalasdeestudiocmd.controller;


import com.ulasalle.sistemadereservasdesalasdeestudiocmd.model.Estudiante;
import com.ulasalle.sistemadereservasdesalasdeestudiocmd.response.ResponseWrapper;
import com.ulasalle.sistemadereservasdesalasdeestudiocmd.services.IEstudianteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final IEstudianteService estudianteService;

    public EstudianteController(IEstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @PostMapping
    public ResponseEntity<?> guardar(
            @RequestBody Estudiante estudiante
    ) {
        try {
            Estudiante guardado = estudianteService.guardar(estudiante);
            return ResponseWrapper.success(guardado, "Estudiante guardado exitosamente").toResponseEntity();
        } catch (Exception e) {
            return ResponseWrapper.error(e.getMessage(), HttpStatus.BAD_REQUEST.value()).toResponseEntity();
        }
    }
    
    @GetMapping("/habilitados")
    public ResponseEntity<?> listarHabilitados() {
        try {
            return ResponseWrapper.success(estudianteService.listarHabilitados(), "Estudiantes habilitados listados exitosamente").toResponseEntity();
        } catch (Exception e) {
            return ResponseWrapper.error(e.getMessage(), HttpStatus.BAD_REQUEST.value()).toResponseEntity();
        }
    }

    @GetMapping("/deshabilitados")
    public ResponseEntity<?> listarDeshabilitados() {
        try {
            return ResponseWrapper.success(estudianteService.listarDeshabilitados(), "Estudiantes deshabilitados listados exitosamente").toResponseEntity();
        } catch (Exception e) {
            return ResponseWrapper.error(e.getMessage(), HttpStatus.BAD_REQUEST.value()).toResponseEntity();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(
            @PathVariable Long id
    ) {
        try {
            Estudiante estudiante = estudianteService.obtenerPorId(id);
            return ResponseWrapper.success(estudiante, "Estudiante encontrado exitosamente").toResponseEntity();
        } catch (Exception e) {
            return ResponseWrapper.error(e.getMessage(), HttpStatus.NOT_FOUND.value()).toResponseEntity();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Long id,
            @RequestBody Estudiante estudiante
    ) {
        try {
            Estudiante actualizado = estudianteService.actualizar(id, estudiante);
            return ResponseWrapper.success(actualizado, "Estudiante actualizado exitosamente").toResponseEntity();
        } catch (Exception e) {
            return ResponseWrapper.error(e.getMessage(), HttpStatus.BAD_REQUEST.value()).toResponseEntity();
        }
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(
            @PathVariable Long id,
            @RequestParam boolean habilitado
    ) {
        try {
            estudianteService.cambiarEstado(id, habilitado);
            String mensaje = habilitado ? "Estudiante habilitado exitosamente" : "Estudiante deshabilitado exitosamente";
            return ResponseWrapper.success(null, mensaje).toResponseEntity();
        } catch (Exception e) {
            return ResponseWrapper.error(e.getMessage(), HttpStatus.BAD_REQUEST.value()).toResponseEntity();
        }
    }

}