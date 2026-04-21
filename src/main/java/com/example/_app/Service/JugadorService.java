package com.example._app.Service;

import com.example._app.DTO.JugadorDTO;
import java.util.List;

public interface JugadorService {
    List<JugadorDTO> getAll();
    JugadorDTO getById(Integer id);
    JugadorDTO create(JugadorDTO dto);
    JugadorDTO update(Integer id, JugadorDTO dto);
    void delete(Integer id);
    List<JugadorDTO> getByEquipo(Integer idEquipo);
    List<JugadorDTO> getConMasDeXGoles(Integer goles);
}