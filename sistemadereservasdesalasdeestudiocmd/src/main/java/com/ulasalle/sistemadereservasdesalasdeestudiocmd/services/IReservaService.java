package com.ulasalle.sistemadereservasdesalasdeestudiocmd.services;

import com.ulasalle.sistemadereservasdesalasdeestudiocmd.dto.ReservaDTO;
import java.util.List;

public interface IReservaService {
    ReservaDTO crearReserva(ReservaDTO reservaDTO);
    List<ReservaDTO> listarReservas();
    List<ReservaDTO> listarPorEstudiante(Long estudianteId);
    List<ReservaDTO> listarPorSala(Long salaId);
    ReservaDTO obtenerPorId(Long id);
    void cancelarReserva(Long id);
}
