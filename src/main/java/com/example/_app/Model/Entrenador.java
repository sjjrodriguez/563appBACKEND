package com.example._app.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "entrenador")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Entrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrenador")
    private Integer idEntrenador;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "especialidad", length = 100)
    private String especialidad;

    @ManyToOne
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;
}