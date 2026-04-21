package com.example._app.Model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "jugador")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jugador")
    private Integer idJugador;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "posicion", length = 50)
    private String posicion;

    @Column(name = "dorsal")
    private Integer dorsal;

    @Column(name = "fecha_nac")
    private LocalDate fechaNac;

    @Column(name = "nacionalidad", length = 100)
    private String nacionalidad;

    @ManyToOne
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;

    @OneToMany(mappedBy = "jugador", cascade = CascadeType.ALL)
    private List<EstadisticasJugador> estadisticas;
}