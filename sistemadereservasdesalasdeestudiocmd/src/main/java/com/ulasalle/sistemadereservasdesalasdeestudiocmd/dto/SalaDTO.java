package com.ulasalle.sistemadereservasdesalasdeestudiocmd.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalaDTO {
    private Long id;
    private String nombre;
    private int capacidad;
    private int diasAnticipoCancelacion;
    private boolean habilitada;
}
