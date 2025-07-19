package com.ulasalle.sistemadereservasdesalasdeestudiocmd.services;

import com.ulasalle.sistemadereservasdesalasdeestudiocmd.model.Estudiante;

import java.util.List;

public interface IEstudianteService {

    // Metodo para guardar un estudiante
    Estudiante guardar(Estudiante estudiante);

    // Metodo para listar todos los estudiantes
    List<Estudiante> listarHabilitados();

    // Metodo para listar todos los estudiantes
    List<Estudiante> listarDeshabilitados();

    // Metodo para obtener un estudiante por su ID
    Estudiante obtenerPorId(Long id);

    // Metodo para actualizar un estudiante
    Estudiante actualizar(Long id, Estudiante estudiante);

    // Metodo para eliminar un estudiante
    void eliminar(Long id);

    // Metodo para cambiar el estado de un estudiante (habilitado/deshabilitado) (0= deshabilitado, 1 = habilitado) (true = habilitado, false = deshabilitado)
    void cambiarEstado(Long id, boolean habilitado);

}
