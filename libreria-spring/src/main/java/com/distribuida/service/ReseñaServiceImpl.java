package com.distribuida.service;

import com.distribuida.dao.ReseñaRepository;
import com.distribuida.model.Reseña;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReseñaServiceImpl implements ReseñaService {

    @Autowired
    private ReseñaRepository reseñaRepository;

    @Override
    public Reseña crearReseña(Reseña reseña) {
        return reseñaRepository.save(reseña);
    }

    @Override
    public List<Reseña> listarReseñasPorLibro(Long libroId) {
        return List.of();
    }

    @Override
    public List<Reseña> obtenerReseñasPorLibroId(Long idLibro) {
        return reseñaRepository.findByLibroId(idLibro);
    }

}

