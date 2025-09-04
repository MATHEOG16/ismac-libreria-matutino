package com.distribuida.service;

import com.distribuida.model.Pedido;
import java.util.List;

public interface PedidoService {
    Pedido crearPedido(Pedido pedido);
    List<Pedido> listarPedidos();
    Pedido buscarPedido(Long id);
}

