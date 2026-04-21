package com.example._app.DTO;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JugadorDTO {
    private Integer idJugador;
    private String nombre;
    private String posicion;
    private Integer dorsal;
    private LocalDate fechaNac;
    private String nacionalidad;
    private Integer idEquipo;
}