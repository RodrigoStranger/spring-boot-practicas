package com.ulasalle.sistemadereservasdesalasdeestudiocmd.services;

import com.ulasalle.sistemadereservasdesalasdeestudiocmd.dto.SalaDTO;
import java.util.List;

public interface ISalaService {
    SalaDTO crearSala(SalaDTO salaDTO);
    List<SalaDTO> listarHabilitadas();
    List<SalaDTO> listarDeshabilitadas();
    SalaDTO obtenerPorId(Long id);
    SalaDTO actualizar(Long id, SalaDTO salaDTO);
    void cambiarEstado(Long id, boolean habilitada);
}
