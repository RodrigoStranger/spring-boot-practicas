package com.ulasalle.sistemadereservasdesalasdeestudiocmd.repository;

import com.ulasalle.sistemadereservasdesalasdeestudiocmd.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IEstudianteJpaRepository extends JpaRepository<Estudiante, Long> {

    Optional<Estudiante> findById(Long id);

    Optional<Estudiante> findByCodigo(String codigo);

    Optional<Estudiante> findByNombreAndApellido(String nombre, String apellido);

    Optional<Estudiante> findByCorreo(String correo);

    List<Estudiante> findByHabilitado(int habilitado);

}