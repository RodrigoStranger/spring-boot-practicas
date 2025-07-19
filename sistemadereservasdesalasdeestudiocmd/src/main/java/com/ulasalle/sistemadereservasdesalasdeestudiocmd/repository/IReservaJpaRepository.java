package com.ulasalle.sistemadereservasdesalasdeestudiocmd.repository;

import com.ulasalle.sistemadereservasdesalasdeestudiocmd.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface IReservaJpaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findBySalaId(Long salaId);
    List<Reserva> findByEstudianteId(Long estudianteId);
    List<Reserva> findByFechaReserva(LocalDate fechaReserva);
    List<Reserva> findBySalaIdAndFechaReserva(Long salaId, LocalDate fechaReserva);
}
