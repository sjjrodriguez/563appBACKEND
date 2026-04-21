package com.example._app.Model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "equipo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipo")
    private Integer idEquipo;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "ciudad", length = 100)
    private String ciudad;

    @Column(name = "fundacion")
    private LocalDate fundacion;

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL)
    private List<Jugador> jugadores;

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL)
    private List<Entrenador> entrenadores;

    @OneToMany(mappedBy = "equipoLocal", cascade = CascadeType.ALL)
    private List<Partido> partidosLocal;

    @OneToMany(mappedBy = "equipoVisita", cascade = CascadeType.ALL)
    private List<Partido> partidosVisita;
}