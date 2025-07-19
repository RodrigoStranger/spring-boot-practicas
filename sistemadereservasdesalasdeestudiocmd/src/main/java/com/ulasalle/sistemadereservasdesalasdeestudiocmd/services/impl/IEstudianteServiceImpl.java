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

    private IEstudianteJpaRepository estudianteRepository;

    public IEstudianteServiceImpl(
            IEstudianteJpaRepository estudianteJpaRepository
    ) {
        this.estudianteRepository = estudianteJpaRepository;
    }

    @Override
    public EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO) {
        Estudiante estudiante = dtoToEntity(estudianteDTO);

        Optional<Estudiante> existePorNombreApellido = estudianteRepository.findByNombreAndApellido(
                estudiante.getNombre(),
                estudiante.getApellido()
        );
        if (existePorNombreApellido.isPresent()) {
            throw new RuntimeException("Nombre y Apellido ya se encuentran registrados");
        }

        Optional<Estudiante> existePorCorreo = estudianteRepository.findByCorreo(estudiante.getCorreo());
        if (existePorCorreo.isPresent()) {
            throw new RuntimeException("Correo ya se encuentra registrado");
        }

        Optional<Estudiante> existePorCodigo = estudianteRepository.findByCodigo(estudiante.getCodigo());
        if (existePorCodigo.isPresent()) {
            throw new RuntimeException("Codigo ya se encuentra registrado");
        }

        estudiante.setHabilitado(1);
        Estudiante guardado = estudianteRepository.save(estudiante);
        return entityToDto(guardado);
    }

    @Override
    public List<EstudianteDTO> listarHabilitados() {
        return estudianteRepository.findByHabilitado(1)
            .stream().map(this::entityToDto).toList();
    }

    @Override
    public List<EstudianteDTO> listarDeshabilitados() {
        return estudianteRepository.findByHabilitado(0)
            .stream().map(this::entityToDto).toList();
    }

    @Override
    public EstudianteDTO obtenerPorId(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        return entityToDto(estudiante);
    }

    @Override
    public EstudianteDTO actualizar(Long id, EstudianteDTO estudianteDTO) {
        Estudiante estudiante = dtoToEntity(estudianteDTO);
        Estudiante estudianteExistente = estudianteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Optional<Estudiante> existePorNombreApellido = estudianteRepository.findByNombreAndApellido(
            estudiante.getNombre(), estudiante.getApellido());
        if (existePorNombreApellido.isPresent() && !existePorNombreApellido.get().getId().equals(id)) {
            throw new RuntimeException("Nombre y Apellido ya se encuentran registrados");
        }

        Optional<Estudiante> existePorCorreo = estudianteRepository.findByCorreo(estudiante.getCorreo());
        if (existePorCorreo.isPresent() && !existePorCorreo.get().getId().equals(id)) {
            throw new RuntimeException("Correo ya se encuentra registrado");
        }

        Optional<Estudiante> existePorCodigo = estudianteRepository.findByCodigo(estudiante.getCodigo());
        if (existePorCodigo.isPresent() && !existePorCodigo.get().getId().equals(id)) {
            throw new RuntimeException("Codigo ya se encuentra registrado");
        }

        estudianteExistente.setNombre(estudiante.getNombre());
        estudianteExistente.setApellido(estudiante.getApellido());
        estudianteExistente.setCorreo(estudiante.getCorreo());
        estudianteExistente.setCodigo(estudiante.getCodigo());
        estudianteExistente.setHabilitado(estudiante.getHabilitado());
        Estudiante actualizado = estudianteRepository.save(estudianteExistente);
        return entityToDto(actualizado);
    }


    @Override
    public void cambiarEstado(Long id, boolean habilitado) {
        Estudiante estudiante = estudianteRepository.findById(id).orElse(null);
        if (estudiante == null) {
            throw new RuntimeException("Estudiante no encontrado");
        }
        estudiante.setHabilitado(habilitado ? 1 : 0);
        estudianteRepository.save(estudiante);
    }

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
        estudiante.setCodigo(dto.getCodigo());
        estudiante.setNombre(dto.getNombre());
        estudiante.setApellido(dto.getApellido());
        estudiante.setCorreo(dto.getCorreo());
        estudiante.setHabilitado(dto.getHabilitado());
        return estudiante;
    }
}