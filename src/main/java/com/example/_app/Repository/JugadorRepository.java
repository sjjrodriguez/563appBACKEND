package com.example._app.Repository;

import com.example._app.Model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Integer> {

    // Consulta nativa 1: Jugadores de un equipo específico
    @Query(value = "SELECT * FROM jugador WHERE id_equipo = :idEquipo", nativeQuery = true)
    List<Jugador> findJugadoresByEquipo(@Param("idEquipo") Integer idEquipo);

    // Consulta nativa 2: Jugadores con más de X goles
    @Query(value = """
            SELECT j.* FROM jugador j
            JOIN estadisticas_jugador e ON j.id_jugador = e.id_jugador
            GROUP BY j.id_jugador
            HAVING SUM(e.goles) > :goles
            """, nativeQuery = true)
    List<Jugador> findJugadoresConMasDeXGoles(@Param("goles") Integer goles);
}