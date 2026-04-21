package com.example._app.Controller;

import com.example._app.DTO.JugadorDTO;
import com.example._app.Service.JugadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
@RequiredArgsConstructor
@Tag(name = "Jugadores", description = "CRUD de jugadores y consultas nativas")
public class JugadorController {

    private final JugadorService jugadorService;

    @GetMapping
    @Operation(summary = "Listar todos los jugadores")
    public ResponseEntity<List<JugadorDTO>> getAll() {
        return ResponseEntity.ok(jugadorService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener jugador por ID")
    public ResponseEntity<JugadorDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(jugadorService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Crear jugador")
    public ResponseEntity<JugadorDTO> create(@RequestBody JugadorDTO dto) {
        return ResponseEntity.ok(jugadorService.create(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar jugador")
    public ResponseEntity<JugadorDTO> update(@PathVariable Integer id, @RequestBody JugadorDTO dto) {
        return ResponseEntity.ok(jugadorService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar jugador")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        jugadorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/equipo/{idEquipo}")
    @Operation(summary = "Jugadores de un equipo específico")
    public ResponseEntity<List<JugadorDTO>> getByEquipo(@PathVariable Integer idEquipo) {
        return ResponseEntity.ok(jugadorService.getByEquipo(idEquipo));
    }

    @GetMapping("/goles/{cantidad}")
    @Operation(summary = "Jugadores con más de X goles")
    public ResponseEntity<List<JugadorDTO>> getConMasDeXGoles(@PathVariable Integer cantidad) {
        return ResponseEntity.ok(jugadorService.getConMasDeXGoles(cantidad));
    }
}