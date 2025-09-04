package com.distribuida.service;

import com.distribuida.dao.LibroRepository;
import com.distribuida.dao.StockRepository;
import com.distribuida.model.Libro;
import com.distribuida.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Override
    public Stock crearStockParaLibro(Long libroId, Integer cantidadInicial) {
        Libro libro = libroRepository.findById(Math.toIntExact(libroId))
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        Stock stock = new Stock();
        stock.setLibro(libro);
        stock.setCantidadDisponible(cantidadInicial);
        stock.setUltimaActualizacion(LocalDate.now());

        return stockRepository.save(stock);
    }

    @Override
    public void actualizarStock(Long libroId, Integer cantidad) {
        Stock stock = stockRepository.findByLibroId(libroId)
                .orElseThrow(() -> new RuntimeException("Stock no encontrado para el libro"));

        stock.setCantidadDisponible(stock.getCantidadDisponible() + cantidad);
        stock.setUltimaActualizacion(LocalDate.now());

        stockRepository.save(stock);
    }

    @Override
    public Integer consultarStock(Long libroId) {
        return stockRepository.findByLibroId(libroId)
                .map(Stock::getCantidadDisponible)
                .orElse(0);
    }
}


