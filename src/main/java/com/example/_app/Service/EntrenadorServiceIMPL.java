package com.example._app.Service;

import com.example._app.DTO.EntrenadorDTO;
import com.example._app.Model.Entrenador;
import com.example._app.Model.Equipo;
import com.example._app.Repository.EntrenadorRepository;
import com.example._app.Repository.EquipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntrenadorServiceIMPL implements EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;
    private final EquipoRepository equipoRepository;

    private EntrenadorDTO toDTO(Entrenador e) {
        return EntrenadorDTO.builder()
                .idEntrenador(e.getIdEntrenador())
                .nombre(e.getNombre())
                .especialidad(e.getEspecialidad())
                .idEquipo(e.getEquipo() != null ? e.getEquipo().getIdEquipo() : null)
                .build();
    }

    private Entrenador toEntity(EntrenadorDTO dto) {
        Equipo equipo = equipoRepository.findById(dto.getIdEquipo())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + dto.getIdEquipo()));
        return Entrenador.builder()
                .idEntrenador(dto.getIdEntrenador())
                .nombre(dto.getNombre())
                .especialidad(dto.getEspecialidad())
                .equipo(equipo)
                .build();
    }

    @Override
    public List<EntrenadorDTO> getAll() {
        return entrenadorRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EntrenadorDTO getById(Integer id) {
        return entrenadorRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado con id: " + id));
    }

    @Override
    public EntrenadorDTO create(EntrenadorDTO dto) {
        return toDTO(entrenadorRepository.save(toEntity(dto)));
    }

    @Override
    public EntrenadorDTO update(Integer id, EntrenadorDTO dto) {
        Entrenador entrenador = entrenadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado con id: " + id));
        entrenador.setNombre(dto.getNombre());
        entrenador.setEspecialidad(dto.getEspecialidad());
        entrenador.setEquipo(equipoRepository.findById(dto.getIdEquipo())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + dto.getIdEquipo())));
        return toDTO(entrenadorRepository.save(entrenador));
    }

    @Override
    public void delete(Integer id) {
        entrenadorRepository.deleteById(id);
    }
}