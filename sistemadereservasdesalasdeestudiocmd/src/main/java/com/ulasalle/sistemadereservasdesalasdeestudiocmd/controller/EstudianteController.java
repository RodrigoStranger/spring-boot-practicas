package com.ulasalle.sistemadereservasdesalasdeestudiocmd.controller;


import com.ulasalle.sistemadereservasdesalasdeestudiocmd.model.Estudiante;
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
            Estudiante guardado = estudianteService.guardar(dtoToEntity(estudianteDTO));
            return ResponseWrapper.success(entityToDto(guardado), "Estudiante guardado exitosamente").toResponseEntity();
        } catch (Exception e) {
            return ResponseWrapper.error(e.getMessage(), HttpStatus.BAD_REQUEST.value()).toResponseEntity();
        }
    }
    
    @GetMapping("/habilitados")
    public ResponseEntity<?> listarHabilitados() {
        try {
            var lista = estudianteService.listarHabilitados().stream()
                .map(this::entityToDto)
                .toList();
            return ResponseWrapper.success(lista, "Estudiantes habilitados listados exitosamente").toResponseEntity();
        } catch (Exception e) {
            return ResponseWrapper.error(e.getMessage(), HttpStatus.BAD_REQUEST.value()).toResponseEntity();
        }
    }

    @GetMapping("/deshabilitados")
    public ResponseEntity<?> listarDeshabilitados() {
        try {
            var lista = estudianteService.listarDeshabilitados().stream()
                .map(this::entityToDto)
                .toList();
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
            Estudiante estudiante = estudianteService.obtenerPorId(id);
            return ResponseWrapper.success(entityToDto(estudiante), "Estudiante encontrado exitosamente").toResponseEntity();
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
            Estudiante actualizado = estudianteService.actualizar(id, dtoToEntity(estudianteDTO));
            return ResponseWrapper.success(entityToDto(actualizado), "Estudiante actualizado exitosamente").toResponseEntity();
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

    private EstudianteDTO entityToDto(Estudiante estudiante) {
        if (estudiante == null) return null;
        EstudianteDTO dto = new EstudianteDTO();
        dto.setId(estudiante.getId());
        dto.setCodigo(estudiante.getCodigo());
        dto.setNombre(estudiante.getNombre());
        dto.setApellido(estudiante.getApellido());
        dto.setCorreo(estudiante.getCorreo());
        dto.setHabilitado(estudiante.getHabilitado());
        return dto;
    }

    private Estudiante dtoToEntity(EstudianteDTO dto) {
        if (dto == null) return null;
        Estudiante estudiante = new Estudiante();
        estudiante.setId(dto.getId());
        estudiante.setCodigo(dto.getCodigo());
        estudiante.setNombre(dto.getNombre());
        estudiante.setApellido(dto.getApellido());
        // estudiante.setCarrera(dto.getCarrera()); // Si tienes el campo en la entidad
        estudiante.setCorreo(dto.getCorreo());
        estudiante.setHabilitado(dto.getHabilitado());
        return estudiante;
    }
}