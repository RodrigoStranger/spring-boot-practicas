package com.ulasalle.sistemadereservasdesalasdeestudiocmd.repository;

import com.ulasalle.sistemadereservasdesalasdeestudiocmd.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IEstudianteJpaRepository extends JpaRepository<Estudiante, Long> {

    // Metodo para buscar estudiantes por su id
    Optional<Estudiante> findById(Long id);

    // Metodo para buscar estudiantes por su codigo
    Optional<Estudiante> findByCodigo(String codigo);

    // Metodo para buscar estudiantes por su nombre y apellido
    Optional<Estudiante> findByNombreAndApellido(String nombre, String apellido);

    // Metodo para buscar estudiantes por su correo
    Optional<Estudiante> findByCorreo(String correo);

    // Metodo para buscar estudiantes habilitados
    List<Estudiante> findByHabilitado(int habilitado);

}
