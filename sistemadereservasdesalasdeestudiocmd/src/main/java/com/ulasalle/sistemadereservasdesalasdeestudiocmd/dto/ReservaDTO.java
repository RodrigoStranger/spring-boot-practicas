package com.ulasalle.sistemadereservasdesalasdeestudiocmd.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ReservaDTO {
    private Long id;
    private Long estudianteId;
    private Long salaId;
    private LocalDate fechaReserva;
    private int cantidadEstudiantes;
}
