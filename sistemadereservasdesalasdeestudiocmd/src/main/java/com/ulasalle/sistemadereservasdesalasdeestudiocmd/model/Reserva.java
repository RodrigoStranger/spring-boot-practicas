package com.ulasalle.sistemadereservasdesalasdeestudiocmd.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "reservas")
@Getter
@Setter
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id_estudiante")
    private Estudiante estudiante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sala", referencedColumnName = "id_sala")
    private Sala sala;

    @Column(name = "fecha_reserva")
    private LocalDate fechaReserva;

    @Column(name = "cantidad_estudiantes")
    private int cantidadEstudiantes;
}
