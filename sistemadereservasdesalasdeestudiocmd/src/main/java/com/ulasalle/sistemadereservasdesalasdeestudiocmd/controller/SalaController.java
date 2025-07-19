package com.ulasalle.sistemadereservasdesalasdeestudiocmd.controller;

import com.ulasalle.sistemadereservasdesalasdeestudiocmd.dto.SalaDTO;
import com.ulasalle.sistemadereservasdesalasdeestudiocmd.response.ResponseWrapper;
import com.ulasalle.sistemadereservasdesalasdeestudiocmd.services.ISalaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas")
public class SalaController {

    private final ISalaService salaService;

    public SalaController(ISalaService salaService) {
        this.salaService = salaService;
    }

    @PostMapping
    public ResponseEntity<?> crearSala(@RequestBody SalaDTO salaDTO) {
        try {
            SalaDTO guardada = salaService.crearSala(salaDTO);
            return ResponseWrapper.success(guardada, "Sala creada exitosamente").toResponseEntity();
        } catch (Exception e) {
            return ResponseWrapper.error(e.getMessage(), HttpStatus.BAD_REQUEST.value()).toResponseEntity();
        }
    }

    @GetMapping("/habilitadas")
    public ResponseEntity<?> listarHabilitadas() {
        List<SalaDTO> lista = salaService.listarHabilitadas();
        return ResponseWrapper.success(lista, "Salas habilitadas listadas exitosamente").toResponseEntity();
    }

    @GetMapping("/deshabilitadas")
    public ResponseEntity<?> listarDeshabilitadas() {
        List<SalaDTO> lista = salaService.listarDeshabilitadas();
        return ResponseWrapper.success(lista, "Salas deshabilitadas listadas exitosamente").toResponseEntity();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        try {
            SalaDTO sala = salaService.obtenerPorId(id);
            return ResponseWrapper.success(sala, "Sala encontrada exitosamente").toResponseEntity();
        } catch (Exception e) {
            return ResponseWrapper.error(e.getMessage(), HttpStatus.NOT_FOUND.value()).toResponseEntity();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody SalaDTO salaDTO) {
        try {
            SalaDTO actualizada = salaService.actualizar(id, salaDTO);
            return ResponseWrapper.success(actualizada, "Sala actualizada exitosamente").toResponseEntity();
        } catch (Exception e) {
            return ResponseWrapper.error(e.getMessage(), HttpStatus.BAD_REQUEST.value()).toResponseEntity();
        }
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(@PathVariable Long id, @RequestParam(name = "habilitada") boolean habilitada) {
        try {
            salaService.cambiarEstado(id, habilitada);
            String mensaje = habilitada ? "Sala habilitada exitosamente" : "Sala deshabilitada exitosamente";
            return ResponseWrapper.success(null, mensaje).toResponseEntity();
        } catch (Exception e) {
            return ResponseWrapper.error(e.getMessage(), HttpStatus.BAD_REQUEST.value()).toResponseEntity();
        }
    }
}
