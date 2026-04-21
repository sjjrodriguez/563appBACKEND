package com.example._app.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estadisticas_jugador")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadisticasJugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estadistica")
    private Integer idEstadistica;

    @ManyToOne
    @JoinColumn(name = "id_jugador")
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "id_partido")
    private Partido partido;

    @Column(name = "minutos_jugados")
    private Integer minutosJugados;

    @Column(name = "goles")
    private Integer goles;

    @Column(name = "asistencias")
    private Integer asistencias;

    @Column(name = "tarjetas_amarillas")
    private Integer tarjetasAmarillas;

    @Column(name = "tarjetas_rojas")
    private Integer tarjetasRojas;
}