package com.ulasalle.sistemadereservasdesalasdeestudiocmd.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "estudiantes")
@Getter
@Setter
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "id_estudiante")
    private Long id;

    @Column(name = "codigo_estudiante")
    private String codigo;

    @Column(name = "nombre_estudiante")
    private String nombre;

    @Column(name = "apellido_estudiante")
    private String apellido;

    @Column(name = "correo_estudiante")
    private String correo;

    @Column(name = "habilitado_estudiante")
    private int habilitado;
    
    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<Reserva> reservas = new java.util.ArrayList<>();

}