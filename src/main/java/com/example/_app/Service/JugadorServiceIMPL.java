package com.example._app.Service;

import com.example._app.DTO.JugadorDTO;
import com.example._app.Model.Equipo;
import com.example._app.Model.Jugador;
import com.example._app.Repository.EquipoRepository;
import com.example._app.Repository.JugadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JugadorServiceIMPL implements JugadorService {

    private final JugadorRepository jugadorRepository;
    private final EquipoRepository equipoRepository;

    private JugadorDTO toDTO(Jugador j) {
        return JugadorDTO.builder()
                .idJugador(j.getIdJugador())
                .nombre(j.getNombre())
                .posicion(j.getPosicion())
                .dorsal(j.getDorsal())
                .fechaNac(j.getFechaNac())
                .nacionalidad(j.getNacionalidad())
                .idEquipo(j.getEquipo() != null ? j.getEquipo().getIdEquipo() : null)
                .build();
    }

    private Jugador toEntity(JugadorDTO dto) {
        Equipo equipo = equipoRepository.findById(dto.getIdEquipo())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + dto.getIdEquipo()));
        return Jugador.builder()
                .idJugador(dto.getIdJugador())
                .nombre(dto.getNombre())
                .posicion(dto.getPosicion())
                .dorsal(dto.getDorsal())
                .fechaNac(dto.getFechaNac())
                .nacionalidad(dto.getNacionalidad())
                .equipo(equipo)
                .build();
    }

    @Override
    public List<JugadorDTO> getAll() {
        return jugadorRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public JugadorDTO getById(Integer id) {
        return jugadorRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado con id: " + id));
    }

    @Override
    public JugadorDTO create(JugadorDTO dto) {
        return toDTO(jugadorRepository.save(toEntity(dto)));
    }

    @Override
    public JugadorDTO update(Integer id, JugadorDTO dto) {
        Jugador jugador = jugadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado con id: " + id));
        jugador.setNombre(dto.getNombre());
        jugador.setPosicion(dto.getPosicion());
        jugador.setDorsal(dto.getDorsal());
        jugador.setFechaNac(dto.getFechaNac());
        jugador.setNacionalidad(dto.getNacionalidad());
        jugador.setEquipo(equipoRepository.findById(dto.getIdEquipo())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + dto.getIdEquipo())));
        return toDTO(jugadorRepository.save(jugador));
    }

    @Override
    public void delete(Integer id) {
        jugadorRepository.deleteById(id);
    }

    @Override
    public List<JugadorDTO> getByEquipo(Integer idEquipo) {
        return jugadorRepository.findJugadoresByEquipo(idEquipo)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<JugadorDTO> getConMasDeXGoles(Integer goles) {
        return jugadorRepository.findJugadoresConMasDeXGoles(goles)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}