package com.ulasalle.sistemadereservasdesalasdeestudiocmd.repository;

import com.ulasalle.sistemadereservasdesalasdeestudiocmd.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ISalaJpaRepository extends JpaRepository<Sala, Long> {
    
    Optional<Sala> findById(Long id);
    
    Optional<Sala> findByNombre(String nombre);
    
    List<Sala> findByHabilitada(boolean habilitada);

    List<Sala> findByDeshabilitada(boolean deshabilitada);

}
