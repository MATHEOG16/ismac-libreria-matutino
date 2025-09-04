package com.distribuida.service;

import com.distribuida.dao.LibroRepository;
import com.distribuida.dao.StockRepository;
import com.distribuida.model.Libro;
import com.distribuida.model.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    private Libro libro;
    private Stock stock;

    @BeforeEach
    void setUp() {
        libro = new Libro();
        libro.setIdLibro(1);
        stock = new Stock();
        stock.setId(1L);
        stock.setLibro(libro);
        stock.setCantidadDisponible(10);
        stock.setUltimaActualizacion(LocalDate.now());
    }

    @Test
    void crearStockParaLibro_deberiaGuardarYRetornarStock() {
        when(libroRepository.findById(1)).thenReturn(Optional.of(libro));
        when(stockRepository.save(any(Stock.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Stock creado = stockService.crearStockParaLibro(1L, 5);

        assertNotNull(creado);
        assertEquals(libro, creado.getLibro());
        assertEquals(5, creado.getCantidadDisponible());
        assertNotNull(creado.getUltimaActualizacion());

        verify(libroRepository, times(1)).findById(1);
        verify(stockRepository, times(1)).save(any(Stock.class));
    }

    @Test
    void actualizarStock_existente_deberiaActualizarYGuardar() {
        when(stockRepository.findByLibroId(1L)).thenReturn(Optional.of(stock));
        when(stockRepository.save(any(Stock.class))).thenReturn(stock);

        stockService.actualizarStock(1L, 5);

        assertEquals(15, stock.getCantidadDisponible());
        verify(stockRepository, times(1)).save(stock);
    }

    @Test
    void consultarStock_existente_deberiaRetornarCantidad() {
        when(stockRepository.findByLibroId(1L)).thenReturn(Optional.of(stock));

        Integer cantidad = stockService.consultarStock(1L);

        assertEquals(10, cantidad);
    }

    @Test
    void consultarStock_noExistente_deberiaRetornarCero() {
        when(stockRepository.findByLibroId(99L)).thenReturn(Optional.empty());

        Integer cantidad = stockService.consultarStock(99L);

        assertEquals(0, cantidad);
    }

    @Test
    void actualizarStock_noExistente_deberiaLanzarExcepcion() {
        when(stockRepository.findByLibroId(2L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                stockService.actualizarStock(2L, 3)
        );

        assertEquals("Stock no encontrado para el libro", ex.getMessage());
    }

    @Test
    void crearStockParaLibro_libroNoExiste_deberiaLanzarExcepcion() {
        when(libroRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                stockService.crearStockParaLibro(1L, 5)
        );

        assertEquals("Libro no encontrado", ex.getMessage());
    }
}

