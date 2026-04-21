package com.example._app.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntrenadorDTO {
    private Integer idEntrenador;
    private String nombre;
    private String especialidad;
    private Integer idEquipo;
}