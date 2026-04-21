package com.example._app.Controller;

import com.example._app.DTO.PartidoDTO;
import com.example._app.Service.PartidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/partidos")
@RequiredArgsConstructor
@Tag(name = "Partidos", description = "CRUD de partidos y consultas nativas")
public class PartidoController {

    private final PartidoService partidoService;

    @GetMapping
    @Operation(summary = "Listar todos los partidos")
    public ResponseEntity<List<PartidoDTO>> getAll() {
        return ResponseEntity.ok(partidoService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener partido por ID")
    public ResponseEntity<PartidoDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(partidoService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Crear partido")
    public ResponseEntity<PartidoDTO> create(@RequestBody PartidoDTO dto) {
        return ResponseEntity.ok(partidoService.create(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar partido")
    public ResponseEntity<PartidoDTO> update(@PathVariable Integer id, @RequestBody PartidoDTO dto) {
        return ResponseEntity.ok(partidoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar partido")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        partidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/goles/equipo/{idEquipo}")
    @Operation(summary = "Total de goles de un equipo en todos sus partidos")
    public ResponseEntity<Integer> getTotalGoles(@PathVariable Integer idEquipo) {
        return ResponseEntity.ok(partidoService.getTotalGoles(idEquipo));
    }

    @GetMapping("/resultados")
    @Operation(summary = "Resultados de todos los partidos con nombres de equipos")
    public ResponseEntity<List<Map<String, Object>>> getResultados() {
        return ResponseEntity.ok(partidoService.getResultados());
    }
}