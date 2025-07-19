package com.ulasalle.sistemadereservasdesalasdeestudiocmd.controller;


import com.ulasalle.sistemadereservasdesalasdeestudiocmd.dto.EstudianteDTO;
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
            @RequestBody EstudianteDTO estudianteDTO
    ) {
        try {
            EstudianteDTO guardado = estudianteService.crearEstudiante(estudianteDTO);
            return ResponseWrapper.success(guardado, "Estudiante guardado exitosamente").toResponseEntity();
        } catch (Exception e) {
            return ResponseWrapper.error(e.getMessage(), HttpStatus.BAD_REQUEST.value()).toResponseEntity();
        }
    }
    
    @GetMapping("/habilitados")
    public ResponseEntity<?> listarHabilitados() {
        try {
            var lista = estudianteService.listarHabilitados();
            return ResponseWrapper.success(lista, "Estudiantes habilitados listados exitosamente").toResponseEntity();
        } catch (Exception e) {
            return ResponseWrapper.error(e.getMessage(), HttpStatus.BAD_REQUEST.value()).toResponseEntity();
        }
    }

    @GetMapping("/deshabilitados")
    public ResponseEntity<?> listarDeshabilitados() {
        try {
            var lista = estudianteService.listarDeshabilitados();
            return ResponseWrapper.success(lista, "Estudiantes deshabilitados listados exitosamente").toResponseEntity();
        } catch (Exception e) {
            return ResponseWrapper.error(e.getMessage(), HttpStatus.BAD_REQUEST.value()).toResponseEntity();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(
            @PathVariable Long id
    ) {
        try {
            EstudianteDTO estudiante = estudianteService.obtenerPorId(id);
            return ResponseWrapper.success(estudiante, "Estudiante encontrado exitosamente").toResponseEntity();
        } catch (Exception e) {
            return ResponseWrapper.error(e.getMessage(), HttpStatus.NOT_FOUND.value()).toResponseEntity();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Long id,
            @RequestBody EstudianteDTO estudianteDTO
    ) {
        try {
            EstudianteDTO actualizado = estudianteService.actualizar(id, estudianteDTO);
            return ResponseWrapper.success(actualizado, "Estudiante actualizado exitosamente").toResponseEntity();
        } catch (Exception e) {
            return ResponseWrapper.error(e.getMessage(), HttpStatus.BAD_REQUEST.value()).toResponseEntity();
        }
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(
            @PathVariable Long id,
            @RequestParam(name = "estado") boolean estado
    ) {
        try {
            estudianteService.cambiarEstado(id, estado);
            String mensaje = estado ? "Estudiante habilitado exitosamente" : "Estudiante deshabilitado exitosamente";
            return ResponseWrapper.success(null, mensaje).toResponseEntity();
        } catch (Exception e) {
            return ResponseWrapper.error(e.getMessage(), HttpStatus.BAD_REQUEST.value()).toResponseEntity();
        }
    }
}