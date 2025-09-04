package com.distribuida.dao;

import com.distribuida.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByEstado(String estado);

    List<Pedido> findByFecha(LocalDate fecha);

    List<Pedido> findByFechaBetween(LocalDate inicio, LocalDate fin);

    @Query("SELECT p FROM Pedido p JOIN p.libros l WHERE l.id = :libroId")
    List<Pedido> findByLibroId(@Param("libroId") Long libroId);
}

