package com.ulasalle.sistemadereservasdesalasdeestudiocmd.services.impl;

import com.ulasalle.sistemadereservasdesalasdeestudiocmd.model.Estudiante;
import com.ulasalle.sistemadereservasdesalasdeestudiocmd.dto.EstudianteDTO;
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
    public EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO) {
        Estudiante estudiante = dtoToEntity(estudianteDTO);

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
        Estudiante guardado = repoEstudiante.save(estudiante);
        return entityToDto(guardado);
    }

    @Override
    public List<EstudianteDTO> listarHabilitados() {
        return repoEstudiante.findByHabilitado(1)
            .stream().map(this::entityToDto).toList();
    }

    @Override
    public List<EstudianteDTO> listarDeshabilitados() {
        return repoEstudiante.findByHabilitado(0)
            .stream().map(this::entityToDto).toList();
    }

    @Override
    public EstudianteDTO obtenerPorId(Long id) {
        Estudiante estudiante = repoEstudiante.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        return entityToDto(estudiante);
    }

    @Override
    public EstudianteDTO actualizar(Long id, EstudianteDTO estudianteDTO) {
        Estudiante estudiante = dtoToEntity(estudianteDTO);
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
        estudianteExistente.setHabilitado(estudiante.getHabilitado());
        Estudiante actualizado = repoEstudiante.save(estudianteExistente);
        return entityToDto(actualizado);
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

    // Métodos de mapeo entre entidad y DTO
    private EstudianteDTO entityToDto(Estudiante estudiante) {
        if (estudiante == null) return null;
        EstudianteDTO dto = new EstudianteDTO();
        dto.setId(estudiante.getId());
        dto.setCodigo(estudiante.getCodigo());
        dto.setNombre(estudiante.getNombre());
        dto.setApellido(estudiante.getApellido());
        dto.setCorreo(estudiante.getCorreo());
        dto.setHabilitado(estudiante.getHabilitado());
        return dto;
    }

    private Estudiante dtoToEntity(EstudianteDTO dto) {
        if (dto == null) return null;
        Estudiante estudiante = new Estudiante();
        estudiante.setId(dto.getId());
        estudiante.setCodigo(dto.getCodigo());
        estudiante.setNombre(dto.getNombre());
        estudiante.setApellido(dto.getApellido());
        // estudiante.setCarrera(dto.getCarrera()); // Si tienes el campo en la entidad
        estudiante.setCorreo(dto.getCorreo());
        estudiante.setHabilitado(dto.getHabilitado());
        return estudiante;
    }
}