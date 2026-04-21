package com.example._app.Service;

import com.example._app.DTO.EstadisticasJugadorDTO;
import com.example._app.Model.EstadisticasJugador;
import com.example._app.Model.Jugador;
import com.example._app.Model.Partido;
import com.example._app.Repository.EstadisticasJugadorRepository;
import com.example._app.Repository.JugadorRepository;
import com.example._app.Repository.PartidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstadisticasJugadorServiceIMPL implements EstadisticasJugadorService {

    private final EstadisticasJugadorRepository estadisticasRepository;
    private final JugadorRepository jugadorRepository;
    private final PartidoRepository partidoRepository;

    private EstadisticasJugadorDTO toDTO(EstadisticasJugador e) {
        return EstadisticasJugadorDTO.builder()
                .idEstadistica(e.getIdEstadistica())
                .idJugador(e.getJugador() != null ? e.getJugador().getIdJugador() : null)
                .idPartido(e.getPartido() != null ? e.getPartido().getIdPartido() : null)
                .minutosJugados(e.getMinutosJugados())
                .goles(e.getGoles())
                .asistencias(e.getAsistencias())
                .tarjetasAmarillas(e.getTarjetasAmarillas())
                .tarjetasRojas(e.getTarjetasRojas())
                .build();
    }

    private EstadisticasJugador toEntity(EstadisticasJugadorDTO dto) {
        Jugador jugador = jugadorRepository.findById(dto.getIdJugador())
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado con id: " + dto.getIdJugador()));
        Partido partido = partidoRepository.findById(dto.getIdPartido())
                .orElseThrow(() -> new RuntimeException("Partido no encontrado con id: " + dto.getIdPartido()));
        return EstadisticasJugador.builder()
                .idEstadistica(dto.getIdEstadistica())
                .jugador(jugador)
                .partido(partido)
                .minutosJugados(dto.getMinutosJugados())
                .goles(dto.getGoles())
                .asistencias(dto.getAsistencias())
                .tarjetasAmarillas(dto.getTarjetasAmarillas())
                .tarjetasRojas(dto.getTarjetasRojas())
                .build();
    }

    @Override
    public List<EstadisticasJugadorDTO> getAll() {
        return estadisticasRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EstadisticasJugadorDTO getById(Integer id) {
        return estadisticasRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Estadística no encontrada con id: " + id));
    }

    @Override
    public EstadisticasJugadorDTO create(EstadisticasJugadorDTO dto) {
        return toDTO(estadisticasRepository.save(toEntity(dto)));
    }

    @Override
    public EstadisticasJugadorDTO update(Integer id, EstadisticasJugadorDTO dto) {
        EstadisticasJugador est = estadisticasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estadística no encontrada con id: " + id));
        est.setJugador(jugadorRepository.findById(dto.getIdJugador())
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado con id: " + dto.getIdJugador())));
        est.setPartido(partidoRepository.findById(dto.getIdPartido())
                .orElseThrow(() -> new RuntimeException("Partido no encontrado con id: " + dto.getIdPartido())));
        est.setMinutosJugados(dto.getMinutosJugados());
        est.setGoles(dto.getGoles());
        est.setAsistencias(dto.getAsistencias());
        est.setTarjetasAmarillas(dto.getTarjetasAmarillas());
        est.setTarjetasRojas(dto.getTarjetasRojas());
        return toDTO(estadisticasRepository.save(est));
    }

    @Override
    public void delete(Integer id) {
        estadisticasRepository.deleteById(id);
    }
}