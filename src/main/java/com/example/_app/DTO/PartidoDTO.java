package com.example._app.DTO;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartidoDTO {
    private Integer idPartido;
    private LocalDate fecha;
    private String estadio;
    private Integer idEquipoLocal;
    private Integer idEquipoVisita;
    private Integer golesLocal;
    private Integer golesVisita;
}