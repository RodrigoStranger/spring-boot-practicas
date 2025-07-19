package com.ulasalle.sistemadereservasdesalasdeestudiocmd.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    
    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL, orphanRemoval = true)
    @com.fasterxml.jackson.annotation.JsonIgnore
    private java.util.List<Reserva> reservas = new java.util.ArrayList<>();

}