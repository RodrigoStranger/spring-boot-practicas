package com.ulasalle.sistemadereservasdesalasdeestudiocmd.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstudianteDTO {
    private Long id;
    private String codigo;
    private String nombre;
    private String apellido;
    private String correo;
    private int habilitado;
}
