package com.ulasalle.sistemadereservasdesalasdeestudiocmd.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaDTO {
    private Long id;
    private Long estudianteId;
    private Long salaId;
    private String fechaReserva;
    private int cantidadEstudiantes;
    private String estado;
}
