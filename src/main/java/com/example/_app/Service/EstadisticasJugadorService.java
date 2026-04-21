package com.example._app.Service;

import com.example._app.DTO.EstadisticasJugadorDTO;
import java.util.List;

public interface EstadisticasJugadorService {
    List<EstadisticasJugadorDTO> getAll();
    EstadisticasJugadorDTO getById(Integer id);
    EstadisticasJugadorDTO create(EstadisticasJugadorDTO dto);
    EstadisticasJugadorDTO update(Integer id, EstadisticasJugadorDTO dto);
    void delete(Integer id);
}