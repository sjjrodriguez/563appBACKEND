package com.example._app.Controller;

import com.example._app.DTO.EstadisticasJugadorDTO;
import com.example._app.Service.EstadisticasJugadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/estadisticas")
@RequiredArgsConstructor
@Tag(name = "Estadísticas", description = "CRUD de estadísticas de jugadores")
public class EstadisticasJugadorController {

    private final EstadisticasJugadorService estadisticasService;

    @GetMapping
    @Operation(summary = "Listar todas las estadísticas")
    public ResponseEntity<List<EstadisticasJugadorDTO>> getAll() {
        return ResponseEntity.ok(estadisticasService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener estadística por ID")
    public ResponseEntity<EstadisticasJugadorDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(estadisticasService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Crear estadística")
    public ResponseEntity<EstadisticasJugadorDTO> create(@RequestBody EstadisticasJugadorDTO dto) {
        return ResponseEntity.ok(estadisticasService.create(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar estadística")
    public ResponseEntity<EstadisticasJugadorDTO> update(@PathVariable Integer id, @RequestBody EstadisticasJugadorDTO dto) {
        return ResponseEntity.ok(estadisticasService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar estadística")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        estadisticasService.delete(id);
        return ResponseEntity.noContent().build();
    }
}