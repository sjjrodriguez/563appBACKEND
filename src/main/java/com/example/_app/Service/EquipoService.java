package com.example._app.Service;

import com.example._app.DTO.EquipoDTO;
import java.util.List;

public interface EquipoService {
    List<EquipoDTO> getAll();
    EquipoDTO getById(Integer id);
    EquipoDTO create(EquipoDTO dto);
    EquipoDTO update(Integer id, EquipoDTO dto);
    void delete(Integer id);
}