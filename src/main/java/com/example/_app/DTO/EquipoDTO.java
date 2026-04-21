package com.example._app.DTO;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipoDTO {
    private Integer idEquipo;
    private String nombre;
    private String ciudad;
    private LocalDate fundacion;
}