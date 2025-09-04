package com.distribuida.dao;

import com.distribuida.model.Reseña;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReseñaRepository extends JpaRepository<Reseña, Long> {
    List<Reseña> findByLibroId(Long libroId);
}
