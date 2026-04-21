package com.example._app.Service;

import com.example._app.DTO.EquipoDTO;
import com.example._app.Model.Equipo;
import com.example._app.Repository.EquipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipoServiceIMPL implements EquipoService {

    private final EquipoRepository equipoRepository;

    private EquipoDTO toDTO(Equipo e) {
        return EquipoDTO.builder()
                .idEquipo(e.getIdEquipo())
                .nombre(e.getNombre())
                .ciudad(e.getCiudad())
                .fundacion(e.getFundacion())
                .build();
    }

    private Equipo toEntity(EquipoDTO dto) {
        return Equipo.builder()
                .idEquipo(dto.getIdEquipo())
                .nombre(dto.getNombre())
                .ciudad(dto.getCiudad())
                .fundacion(dto.getFundacion())
                .build();
    }

    @Override
    public List<EquipoDTO> getAll() {
        return equipoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EquipoDTO getById(Integer id) {
        return equipoRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + id));
    }

    @Override
    public EquipoDTO create(EquipoDTO dto) {
        return toDTO(equipoRepository.save(toEntity(dto)));
    }

    @Override
    public EquipoDTO update(Integer id, EquipoDTO dto) {
        Equipo equipo = equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + id));
        equipo.setNombre(dto.getNombre());
        equipo.setCiudad(dto.getCiudad());
        equipo.setFundacion(dto.getFundacion());
        return toDTO(equipoRepository.save(equipo));
    }

    @Override
    public void delete(Integer id) {
        equipoRepository.deleteById(id);
    }
}