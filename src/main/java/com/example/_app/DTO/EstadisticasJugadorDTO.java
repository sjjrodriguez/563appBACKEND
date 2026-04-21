package com.example._app.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadisticasJugadorDTO {
    private Integer idEstadistica;
    private Integer idJugador;
    private Integer idPartido;
    private Integer minutosJugados;
    private Integer goles;
    private Integer asistencias;
    private Integer tarjetasAmarillas;
    private Integer tarjetasRojas;
}