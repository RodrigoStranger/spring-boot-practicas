package com.ulasalle.sistemadereservasdesalasdeestudiocmd.services.impl;

import com.ulasalle.sistemadereservasdesalasdeestudiocmd.dto.ReservaDTO;
import com.ulasalle.sistemadereservasdesalasdeestudiocmd.model.Reserva;
import com.ulasalle.sistemadereservasdesalasdeestudiocmd.model.Estudiante;
import com.ulasalle.sistemadereservasdesalasdeestudiocmd.model.Sala;
import com.ulasalle.sistemadereservasdesalasdeestudiocmd.repository.IReservaJpaRepository;
import com.ulasalle.sistemadereservasdesalasdeestudiocmd.repository.IEstudianteJpaRepository;
import com.ulasalle.sistemadereservasdesalasdeestudiocmd.repository.ISalaJpaRepository;
import com.ulasalle.sistemadereservasdesalasdeestudiocmd.services.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

@Service
public class IReservaServiceImpl implements IReservaService {

    @Autowired
    private IReservaJpaRepository reservaRepository;
    @Autowired
    private IEstudianteJpaRepository estudianteRepository;
    @Autowired
    private ISalaJpaRepository salaRepository;

    @Override
    @Transactional
    public ReservaDTO crearReserva(ReservaDTO reservaDTO) {
        // Validación de existencia de estudiante y sala
        Estudiante estudiante = estudianteRepository.findById(reservaDTO.getEstudianteId())
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        Sala sala = salaRepository.findById(reservaDTO.getSalaId())
            .orElseThrow(() -> new RuntimeException("Sala no encontrada"));

        // Validación de sala habilitada
        if (!sala.getHabilitada()) {
            throw new RuntimeException("No se puede reservar una sala deshabilitada");
        }

        // Validación de capacidad
        if (reservaDTO.getCantidadEstudiantes() > sala.getCapacidad()) {
            throw new RuntimeException("La cantidad de estudiantes excede la capacidad de la sala");
        }

        // Validación de reserva duplicada en la misma fecha y sala
        List<Reserva> reservasEnFecha = reservaRepository.findBySalaIdAndFechaReserva(sala.getId(), reservaDTO.getFechaReserva());
        if (!reservasEnFecha.isEmpty()) {
        // Validación de fecha pasada
        if (reservaDTO.getFechaReserva().isBefore(LocalDate.now())) {
            throw new RuntimeException("No se puede reservar para una fecha pasada");
        }

        // Validación de anticipo mínimo
        if (LocalDate.now().plusDays(sala.getDiasAnticipoCancelacion()).isAfter(reservaDTO.getFechaReserva())) {
            throw new RuntimeException("La reserva debe hacerse con el anticipo mínimo requerido por la sala");
        }
            throw new RuntimeException("Ya existe una reserva para esta sala en la fecha indicada");
        }

        Reserva reserva = new Reserva();
        reserva.setEstudiante(estudiante);
        reserva.setSala(sala);
        reserva.setFechaReserva(reservaDTO.getFechaReserva());
        reserva.setCantidadEstudiantes(reservaDTO.getCantidadEstudiantes());

        Reserva guardada = reservaRepository.save(reserva);
        return toDTO(guardada);
    }

    @Override
    @Transactional
    public ReservaDTO actualizarReserva(Long id, ReservaDTO reservaDTO) {
        Reserva reserva = reservaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        Estudiante estudiante = estudianteRepository.findById(reservaDTO.getEstudianteId())
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        Sala sala = salaRepository.findById(reservaDTO.getSalaId())
            .orElseThrow(() -> new RuntimeException("Sala no encontrada"));

        // Validación de sala habilitada
        if (!sala.getHabilitada()) {
            throw new RuntimeException("No se puede reservar una sala deshabilitada");
        }

        if (reservaDTO.getCantidadEstudiantes() > sala.getCapacidad()) {
            throw new RuntimeException("La cantidad de estudiantes excede la capacidad de la sala");
        }

        List<Reserva> reservasEnFecha = reservaRepository.findBySalaIdAndFechaReserva(sala.getId(), reservaDTO.getFechaReserva());
        if (!reservasEnFecha.isEmpty() && reservasEnFecha.stream().anyMatch(r -> !r.getId().equals(id))) {
            throw new RuntimeException("Ya existe una reserva para esta sala en la fecha indicada");
        }

        reserva.setEstudiante(estudiante);
        reserva.setSala(sala);
        reserva.setFechaReserva(reservaDTO.getFechaReserva());
        reserva.setCantidadEstudiantes(reservaDTO.getCantidadEstudiantes());

        Reserva actualizada = reservaRepository.save(reserva);
        return toDTO(actualizada);
    }

    @Override
    @Transactional
    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    @Override
    public ReservaDTO obtenerReservaPorId(Long id) {
        Reserva reserva = reservaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        return toDTO(reserva);
    }

    @Override
    public List<ReservaDTO> obtenerReservas() {
        return reservaRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReservaDTO> obtenerReservasPorSala(Long salaId) {
        return reservaRepository.findBySalaId(salaId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReservaDTO> obtenerReservasPorEstudiante(Long estudianteId) {
        return reservaRepository.findByEstudianteId(estudianteId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReservaDTO> obtenerReservasPorFecha(LocalDate fechaReserva) {
        return reservaRepository.findByFechaReserva(fechaReserva).stream().map(this::toDTO).collect(Collectors.toList());
    }

    // Conversión entre entidad y DTO
    private ReservaDTO toDTO(Reserva reserva) {
        ReservaDTO dto = new ReservaDTO();
        dto.setId(reserva.getId());
        dto.setEstudianteId(reserva.getEstudiante().getId());
        dto.setSalaId(reserva.getSala().getId());
        dto.setFechaReserva(reserva.getFechaReserva());
        dto.setCantidadEstudiantes(reserva.getCantidadEstudiantes());
        return dto;
    }
}
