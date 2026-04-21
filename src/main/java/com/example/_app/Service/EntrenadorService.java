package com.example._app.Service;

import com.example._app.DTO.EntrenadorDTO;
import java.util.List;

public interface EntrenadorService {
    List<EntrenadorDTO> getAll();
    EntrenadorDTO getById(Integer id);
    EntrenadorDTO create(EntrenadorDTO dto);
    EntrenadorDTO update(Integer id, EntrenadorDTO dto);
    void delete(Integer id);
}