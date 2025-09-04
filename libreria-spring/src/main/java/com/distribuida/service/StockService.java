package com.distribuida.service;

import com.distribuida.model.Stock;

public interface StockService {

    Stock crearStockParaLibro(Long libroId, Integer cantidadInicial);
    void actualizarStock(Long libroId, Integer cantidad);
    Integer consultarStock(Long libroId);
}

