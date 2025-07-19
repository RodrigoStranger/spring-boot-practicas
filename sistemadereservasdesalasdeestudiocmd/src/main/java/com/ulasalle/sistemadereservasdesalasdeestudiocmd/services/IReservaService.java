package com.ulasalle.sistemadereservasdesalasdeestudiocmd.services;

import com.ulasalle.sistemadereservasdesalasdeestudiocmd.dto.ReservaDTO;
import java.util.List;

public interface IReservaService {
    ReservaDTO crearReserva(ReservaDTO reservaDTO);
    ReservaDTO actualizarReserva(Long id, ReservaDTO reservaDTO);
    void eliminarReserva(Long id);
    ReservaDTO obtenerReservaPorId(Long id);
    List<ReservaDTO> obtenerReservas();
    List<ReservaDTO> obtenerReservasPorSala(Long salaId);
    List<ReservaDTO> obtenerReservasPorEstudiante(Long estudianteId);
    List<ReservaDTO> obtenerReservasPorFecha(java.time.LocalDate fechaReserva);
}
