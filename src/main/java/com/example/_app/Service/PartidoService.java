package com.example._app.Service;

import com.example._app.DTO.PartidoDTO;
import java.util.List;
import java.util.Map;

public interface PartidoService {
    List<PartidoDTO> getAll();
    PartidoDTO getById(Integer id);
    PartidoDTO create(PartidoDTO dto);
    PartidoDTO update(Integer id, PartidoDTO dto);
    void delete(Integer id);
    Integer getTotalGoles(Integer idEquipo);
    List<Map<String, Object>> getResultados();
}