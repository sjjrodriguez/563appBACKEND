package com.example._app.Model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "partido")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_partido")
    private Integer idPartido;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "estadio", length = 100)
    private String estadio;

    @ManyToOne
    @JoinColumn(name = "equipo_local")
    private Equipo equipoLocal;

    @ManyToOne
    @JoinColumn(name = "equipo_visita")
    private Equipo equipoVisita;

    @Column(name = "goles_local")
    private Integer golesLocal;

    @Column(name = "goles_visita")
    private Integer golesVisita;

    @OneToMany(mappedBy = "partido", cascade = CascadeType.ALL)
    private List<EstadisticasJugador> estadisticas;
}