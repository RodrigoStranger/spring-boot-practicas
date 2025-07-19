package com.ulasalle.sistemadereservasdesalasdeestudiocmd.services;

import com.ulasalle.sistemadereservasdesalasdeestudiocmd.dto.EstudianteDTO;

import java.util.List;

public interface IEstudianteService {
    EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO);
    List<EstudianteDTO> listarHabilitados();
    List<EstudianteDTO> listarDeshabilitados();
    EstudianteDTO obtenerPorId(Long id);
    EstudianteDTO actualizar(Long id, EstudianteDTO estudianteDTO);
    void cambiarEstado(Long id, boolean habilitado);
}