package com.ulasalle.sistemadereservasdesalasdeestudiocmd.services.impl;

import com.ulasalle.sistemadereservasdesalasdeestudiocmd.dto.SalaDTO;
import com.ulasalle.sistemadereservasdesalasdeestudiocmd.model.Sala;
import com.ulasalle.sistemadereservasdesalasdeestudiocmd.repository.ISalaJpaRepository;
import com.ulasalle.sistemadereservasdesalasdeestudiocmd.services.ISalaService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ISalaServiceImpl implements ISalaService {

    private final ISalaJpaRepository salaRepository;

    @Autowired
    public ISalaServiceImpl(ISalaJpaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    @Override
    public SalaDTO crearSala(SalaDTO salaDTO) {
        if (salaRepository.findByNombre(salaDTO.getNombre()).isPresent()) {
            throw new RuntimeException("Ya existe una sala con ese nombre");
        }
        Sala sala = dtoToEntity(salaDTO);
        sala.setHabilitada(true); // Por defecto habilitada
        Sala guardada = salaRepository.save(sala);
        return entityToDto(guardada);
    }

    @Override
    public List<SalaDTO> listarHabilitadas() {
        return salaRepository.findByHabilitada(true)
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalaDTO> listarDeshabilitadas() {
        return salaRepository.findByHabilitada(false)
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SalaDTO obtenerPorId(Long id) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sala no encontrada"));
        return entityToDto(sala);
    }

    @Override
    public SalaDTO actualizar(Long id, SalaDTO salaDTO) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sala no encontrada"));
        var salaExistente = salaRepository.findByNombre(salaDTO.getNombre());
        if (salaExistente.isPresent() && !salaExistente.get().getId().equals(id)) {
            throw new RuntimeException("Ya existe una sala con ese nombre");
        }
        sala.setNombre(salaDTO.getNombre());
        sala.setCapacidad(salaDTO.getCapacidad());
        sala.setDiasAnticipoCancelacion(salaDTO.getDiasAnticipoCancelacion());
        sala.setHabilitada(salaDTO.getHabilitada());
        Sala actualizada = salaRepository.save(sala);
        return entityToDto(actualizada);
    }

    @Override
    public void cambiarEstado(Long id, boolean habilitada) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sala no encontrada"));
        sala.setHabilitada(habilitada);
        salaRepository.save(sala);
    }

    private SalaDTO entityToDto(Sala sala) {
        if (sala == null) return null;
        SalaDTO dto = new SalaDTO();
        dto.setId(sala.getId());
        dto.setNombre(sala.getNombre());
        dto.setCapacidad(sala.getCapacidad());
        dto.setDiasAnticipoCancelacion(sala.getDiasAnticipoCancelacion());
        dto.setHabilitada(sala.getHabilitada());
        return dto;
    }

    private Sala dtoToEntity(SalaDTO dto) {
        if (dto == null) return null;
        Sala sala = new Sala();
        sala.setId(dto.getId());
        sala.setNombre(dto.getNombre());
        sala.setCapacidad(dto.getCapacidad());
        sala.setDiasAnticipoCancelacion(dto.getDiasAnticipoCancelacion());
        sala.setHabilitada(dto.getHabilitada());
        return sala;
    }
}
