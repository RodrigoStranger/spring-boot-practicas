package com.ulasalle.sistemadereservasdesalasdeestudiocmd.repository;

import com.ulasalle.sistemadereservasdesalasdeestudiocmd.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface IReservaJpaRepository extends JpaRepository<Reserva, Long> {
    
    Optional<Reserva> findById(Long id);
    
    List<Reserva> findByEstudianteId(Long estudianteId);
    
    List<Reserva> findBySalaId(Long salaId);
    
    List<Reserva> findByFechaReserva(String fechaReserva);
    
    List<Reserva> findByEstado(String estado);
}
