package com.ulasalle.sistemadereservasdesalasdeestudiocmd.services.impl;

import com.ulasalle.sistemadereservasdesalasdeestudiocmd.model.Estudiante;
import com.ulasalle.sistemadereservasdesalasdeestudiocmd.repository.IEstudianteJpaRepository;
import com.ulasalle.sistemadereservasdesalasdeestudiocmd.services.IEstudianteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IEstudianteServiceImpl implements IEstudianteService {

    private final IEstudianteJpaRepository repoEstudiante;

    public IEstudianteServiceImpl(
            IEstudianteJpaRepository estudianteJpaRepository
    ) {
        this.repoEstudiante = estudianteJpaRepository;
    }

    @Override
    public Estudiante guardar(Estudiante estudiante) {

        Optional<Estudiante> existePorNombreApellido = repoEstudiante.findByNombreAndApellido(
                estudiante.getNombre(),
                estudiante.getApellido()
        );
        if (existePorNombreApellido.isPresent()) {
            throw new RuntimeException("Nombre y Apellido ya se encuentran registrados");
        }

        Optional<Estudiante> existePorCorreo = repoEstudiante.findByCorreo(estudiante.getCorreo());
        if (existePorCorreo.isPresent()) {
            throw new RuntimeException("Correo ya se encuentra registrado");
        }

        Optional<Estudiante> existePorCodigo = repoEstudiante.findByCodigo(estudiante.getCodigo());
        if (existePorCodigo.isPresent()) {
            throw new RuntimeException("Codigo ya se encuentra registrado");
        }

        estudiante.setHabilitado(1);
        return repoEstudiante.save(estudiante);
    }

    @Override
    public List<Estudiante> listarHabilitados() {
        return repoEstudiante.findByHabilitado(1);
    }

    @Override
    public List<Estudiante> listarDeshabilitados() {
        return repoEstudiante.findByHabilitado(0);
    }

    @Override
    public Estudiante obtenerPorId(Long id) {
        return repoEstudiante.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

    }

    @Override
    public Estudiante actualizar(Long id, Estudiante estudiante) {
        Estudiante estudianteExistente = repoEstudiante.findById(id)
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Optional<Estudiante> existePorNombreApellido = repoEstudiante.findByNombreAndApellido(
            estudiante.getNombre(), estudiante.getApellido());
        if (existePorNombreApellido.isPresent() && !existePorNombreApellido.get().getId().equals(id)) {
            throw new RuntimeException("Nombre y Apellido ya se encuentran registrados");
        }

        Optional<Estudiante> existePorCorreo = repoEstudiante.findByCorreo(estudiante.getCorreo());
        if (existePorCorreo.isPresent() && !existePorCorreo.get().getId().equals(id)) {
            throw new RuntimeException("Correo ya se encuentra registrado");
        }

        Optional<Estudiante> existePorCodigo = repoEstudiante.findByCodigo(estudiante.getCodigo());
        if (existePorCodigo.isPresent() && !existePorCodigo.get().getId().equals(id)) {
            throw new RuntimeException("Codigo ya se encuentra registrado");
        }

        estudianteExistente.setNombre(estudiante.getNombre());
        estudianteExistente.setApellido(estudiante.getApellido());
        estudianteExistente.setCorreo(estudiante.getCorreo());
        estudianteExistente.setCodigo(estudiante.getCodigo());
        return repoEstudiante.save(estudianteExistente);
    }

    @Override
    public void eliminar(Long id) {
        Estudiante entidadActual = repoEstudiante.findById(id).orElse(null);
        if (entidadActual == null) {
            throw new RuntimeException("Estudiante no encontrado");
        }
        entidadActual.setHabilitado(0);
        repoEstudiante.save(entidadActual);
    }

    @Override
    public void cambiarEstado(Long id, boolean habilitado) {
        Estudiante estudiante = repoEstudiante.findById(id).orElse(null);
        if (estudiante == null) {
            throw new RuntimeException("Estudiante no encontrado");
        }
        estudiante.setHabilitado(habilitado ? 1 : 0);
        repoEstudiante.save(estudiante);
    }

}