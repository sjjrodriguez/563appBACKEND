package com.example._app.Controller;

import com.example._app.DTO.EntrenadorDTO;
import com.example._app.Service.EntrenadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/entrenadores")
@RequiredArgsConstructor
@Tag(name = "Entrenadores", description = "CRUD de entrenadores")
public class EntrenadorController {

    private final EntrenadorService entrenadorService;

    @GetMapping
    @Operation(summary = "Listar todos los entrenadores")
    public ResponseEntity<List<EntrenadorDTO>> getAll() {
        return ResponseEntity.ok(entrenadorService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener entrenador por ID")
    public ResponseEntity<EntrenadorDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(entrenadorService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Crear entrenador")
    public ResponseEntity<EntrenadorDTO> create(@RequestBody EntrenadorDTO dto) {
        return ResponseEntity.ok(entrenadorService.create(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar entrenador")
    public ResponseEntity<EntrenadorDTO> update(@PathVariable Integer id, @RequestBody EntrenadorDTO dto) {
        return ResponseEntity.ok(entrenadorService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar entrenador")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        entrenadorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}