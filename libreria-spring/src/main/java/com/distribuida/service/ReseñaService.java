package com.distribuida.service;

import com.distribuida.model.Reseña;
import java.util.List;

public interface ReseñaService {
    Reseña crearReseña(Reseña reseña);
    List<Reseña> listarReseñasPorLibro(Long libroId);

    List<Reseña> obtenerReseñasPorLibroId(Long idLibro);
}

