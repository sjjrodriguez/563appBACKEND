package com.example._app.Controller;

import com.example._app.DTO.EquipoDTO;
import com.example._app.Service.EquipoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/equipos")
@RequiredArgsConstructor
@Tag(name = "Equipos", description = "CRUD de equipos")
public class EquipoController {

    private final EquipoService equipoService;

    @GetMapping
    @Operation(summary = "Listar todos los equipos")
    public ResponseEntity<List<EquipoDTO>> getAll() {
        return ResponseEntity.ok(equipoService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener equipo por ID")
    public ResponseEntity<EquipoDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(equipoService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Crear equipo")
    public ResponseEntity<EquipoDTO> create(@RequestBody EquipoDTO dto) {
        return ResponseEntity.ok(equipoService.create(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar equipo")
    public ResponseEntity<EquipoDTO> update(@PathVariable Integer id, @RequestBody EquipoDTO dto) {
        return ResponseEntity.ok(equipoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar equipo")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        equipoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}