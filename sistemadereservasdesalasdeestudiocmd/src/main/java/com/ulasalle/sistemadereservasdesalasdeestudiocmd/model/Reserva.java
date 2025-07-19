package com.ulasalle.sistemadereservasdesalasdeestudiocmd.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "reservas")
@Getter
@Setter
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;

    @Column(name = "fecha_reserva")
    private String fechaReserva;

    @Column(name = "cantidad_estudiantes")
    private int cantidadEstudiantes;

    @Column(name = "estado_reserva")
    private String estado;
}
