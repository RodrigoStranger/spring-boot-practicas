package com.ulasalle.sistemadereservasdesalasdeestudiocmd.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "salas")
@Getter
@Setter
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sala")
    private Long id;

    @Column(name = "nombre_sala")
    private String nombre;

    @Column(name = "capacidad_sala")
    private int capacidad;

    @Column(name = "dias_anticipo_cancelacion")
    private int diasAnticipoCancelacion;

    @Column(name = "habilitada_sala")
    private boolean habilitada;

    public boolean getHabilitada() {
        return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }

    @OneToMany(mappedBy = "sala")
    private List<Reserva> reservas;
}
