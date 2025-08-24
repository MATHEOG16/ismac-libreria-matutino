package com.distribuida.dao;

import com.distribuida.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByEstado(String estado);

    List<Pedido> findByFecha(LocalDate fecha);

    List<Pedido> findByFechaBetween(LocalDate inicio, LocalDate fin);

    List<Pedido> findByLibrosId(Long libroId);
}

