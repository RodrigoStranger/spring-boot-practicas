package com.ulasalle.sistemadereservasdesalasdeestudiocmd.controller;

import com.ulasalle.sistemadereservasdesalasdeestudiocmd.dto.ReservaDTO;
import com.ulasalle.sistemadereservasdesalasdeestudiocmd.response.ResponseWrapper;
import com.ulasalle.sistemadereservasdesalasdeestudiocmd.services.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private IReservaService reservaService;

    @PostMapping
    public ResponseEntity<?> crearReserva(@RequestBody ReservaDTO reservaDTO) {
        try {
            ReservaDTO creada = reservaService.crearReserva(reservaDTO);
            return ResponseWrapper.success(creada, "Reserva creada exitosamente").toResponseEntity();
        } catch (Exception e) {
            return ResponseWrapper.error(e.getMessage(), 400).toResponseEntity();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarReserva(@PathVariable Long id, @RequestBody ReservaDTO reservaDTO) {
        try {
            ReservaDTO actualizada = reservaService.actualizarReserva(id, reservaDTO);
            return ResponseWrapper.success(actualizada, "Reserva actualizada exitosamente").toResponseEntity();
        } catch (Exception e) {
            return ResponseWrapper.error(e.getMessage(), 400).toResponseEntity();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarReserva(@PathVariable Long id) {
        try {
            reservaService.eliminarReserva(id);
            return ResponseWrapper.success(null, "Reserva eliminada exitosamente").toResponseEntity();
        } catch (Exception e) {
            return ResponseWrapper.error(e.getMessage(), 404).toResponseEntity();
        }
    }

    @GetMapping
    public ResponseEntity<?> obtenerReservas() {
        try {
            List<ReservaDTO> reservas = reservaService.obtenerReservas();
            return ResponseWrapper.success(reservas, "Reservas listadas exitosamente").toResponseEntity();
        } catch (Exception e) {
            return ResponseWrapper.error(e.getMessage(), 400).toResponseEntity();
        }
    }

    @GetMapping("/sala/{salaId}")
    public ResponseEntity<?> obtenerReservasPorSala(@PathVariable Long salaId) {
        try {
            List<ReservaDTO> reservas = reservaService.obtenerReservasPorSala(salaId);
            return ResponseWrapper.success(reservas, "Reservas de la sala listadas exitosamente").toResponseEntity();
        } catch (Exception e) {
            return ResponseWrapper.error(e.getMessage(), 400).toResponseEntity();
        }
    }

    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<?> obtenerReservasPorEstudiante(@PathVariable Long estudianteId) {
        try {
            List<ReservaDTO> reservas = reservaService.obtenerReservasPorEstudiante(estudianteId);
            return ResponseWrapper.success(reservas, "Reservas del estudiante listadas exitosamente").toResponseEntity();
        } catch (Exception e) {
            return ResponseWrapper.error(e.getMessage(), 400).toResponseEntity();
        }
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<?> obtenerReservasPorFecha(@PathVariable("fecha") String fecha) {
        try {
            java.time.LocalDate fechaReserva = java.time.LocalDate.parse(fecha);
            List<ReservaDTO> reservas = reservaService.obtenerReservasPorFecha(fechaReserva);
            return ResponseWrapper.success(reservas, "Reservas por fecha listadas exitosamente").toResponseEntity();
        } catch (Exception e) {
            return ResponseWrapper.error(e.getMessage(), 400).toResponseEntity();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerReservaPorId(@PathVariable Long id) {
        try {
            ReservaDTO reserva = reservaService.obtenerReservaPorId(id);
            return ResponseWrapper.success(reserva, "Reserva encontrada exitosamente").toResponseEntity();
        } catch (Exception e) {
            return ResponseWrapper.error(e.getMessage(), 404).toResponseEntity();
        }
    }
}