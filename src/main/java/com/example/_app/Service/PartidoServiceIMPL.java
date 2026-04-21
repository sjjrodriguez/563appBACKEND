package com.example._app.Service;

import com.example._app.DTO.PartidoDTO;
import com.example._app.Model.Equipo;
import com.example._app.Model.Partido;
import com.example._app.Repository.EquipoRepository;
import com.example._app.Repository.PartidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartidoServiceIMPL implements PartidoService {

    private final PartidoRepository partidoRepository;
    private final EquipoRepository equipoRepository;

    private PartidoDTO toDTO(Partido p) {
        return PartidoDTO.builder()
                .idPartido(p.getIdPartido())
                .fecha(p.getFecha())
                .estadio(p.getEstadio())
                .idEquipoLocal(p.getEquipoLocal() != null ? p.getEquipoLocal().getIdEquipo() : null)
                .idEquipoVisita(p.getEquipoVisita() != null ? p.getEquipoVisita().getIdEquipo() : null)
                .golesLocal(p.getGolesLocal())
                .golesVisita(p.getGolesVisita())
                .build();
    }

    private Partido toEntity(PartidoDTO dto) {
        Equipo local = equipoRepository.findById(dto.getIdEquipoLocal())
                .orElseThrow(() -> new RuntimeException("Equipo local no encontrado con id: " + dto.getIdEquipoLocal()));
        Equipo visita = equipoRepository.findById(dto.getIdEquipoVisita())
                .orElseThrow(() -> new RuntimeException("Equipo visita no encontrado con id: " + dto.getIdEquipoVisita()));
        return Partido.builder()
                .idPartido(dto.getIdPartido())
                .fecha(dto.getFecha())
                .estadio(dto.getEstadio())
                .equipoLocal(local)
                .equipoVisita(visita)
                .golesLocal(dto.getGolesLocal())
                .golesVisita(dto.getGolesVisita())
                .build();
    }

    @Override
    public List<PartidoDTO> getAll() {
        return partidoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PartidoDTO getById(Integer id) {
        return partidoRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado con id: " + id));
    }

    @Override
    public PartidoDTO create(PartidoDTO dto) {
        return toDTO(partidoRepository.save(toEntity(dto)));
    }

    @Override
    public PartidoDTO update(Integer id, PartidoDTO dto) {
        Partido partido = partidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado con id: " + id));
        partido.setFecha(dto.getFecha());
        partido.setEstadio(dto.getEstadio());
        partido.setEquipoLocal(equipoRepository.findById(dto.getIdEquipoLocal())
                .orElseThrow(() -> new RuntimeException("Equipo local no encontrado con id: " + dto.getIdEquipoLocal())));
        partido.setEquipoVisita(equipoRepository.findById(dto.getIdEquipoVisita())
                .orElseThrow(() -> new RuntimeException("Equipo visita no encontrado con id: " + dto.getIdEquipoVisita())));
        partido.setGolesLocal(dto.getGolesLocal());
        partido.setGolesVisita(dto.getGolesVisita());
        return toDTO(partidoRepository.save(partido));
    }

    @Override
    public void delete(Integer id) {
        partidoRepository.deleteById(id);
    }

    @Override
    public Integer getTotalGoles(Integer idEquipo) {
        return partidoRepository.getTotalGolesByEquipo(idEquipo);
    }

    @Override
    public List<Map<String, Object>> getResultados() {
        return partidoRepository.getResultadosPartidos()
                .stream()
                .map(row -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("idPartido", row[0]);
                    map.put("fecha", row[1]);
                    map.put("estadio", row[2]);
                    map.put("equipoLocal", row[3]);
                    map.put("equipoVisita", row[4]);
                    map.put("golesLocal", row[5]);
                    map.put("golesVisita", row[6]);
                    return map;
                })
                .collect(Collectors.toList());
    }
}