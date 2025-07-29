package com.distribuida.service;

import com.distribuida.dao.*;
import com.distribuida.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FacturaDetalleServicioTestUnitaria {

    @Mock
    private FacturaDetalleRepository facturaDetalleRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private FacturaRepository facturaRepository;

    @Mock
    private LibroRepository libroRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private FacturaDetalleServiceImpl facturaDetalleService;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @InjectMocks
    private FacturaServiceImpl facturaService;

    @InjectMocks
    private LibroServiceImpl libroService;

    @InjectMocks
    private CategoriaServiceImpl categoriaService;

    @InjectMocks
    private AutorServiceImpl autorService;

    private FacturaDetalle facturaDetalle;
    private Cliente cliente;
    private Factura factura;
    private Libro libro;
    private Categoria categoria;
    private Autor autor;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        facturaDetalle = new FacturaDetalle(1, 2, 40.00, factura, libro);
        factura = new Factura(1, "FAC-0001", new Date(), 100.00, 15.00, 115.00, cliente);
        libro = new Libro(1,"El final", "Twil", 200, "Aniversario", "Español", new Date(),"Un cataclismo se dio en el mundo", "Pasta dura", "054866255", 20, "Especial", "Fisico", 30.00, categoria, autor);
        cliente = new Cliente(1,"1722486327", "Juan", "Taipe", "Av. por ahi", "0981453698", "correo@gmail.com");
        categoria = new Categoria(1, "Romance", "El amor esta en el aire");
        autor = new Autor(1, "Forencio", "Castro", "Ecuador", "Av. Ahi", "0985332586", "autor@gmail.com");
    }

    @Test
    public void testFindAll(){
        when(facturaDetalleRepository.findAll()).thenReturn(Arrays.asList(facturaDetalle));
        List<FacturaDetalle> facturaDetalles = facturaDetalleService.findAll();
        assertNotNull(facturaDetalles);
        assertEquals(1, facturaDetalles.size());
        verify(facturaDetalleRepository, times(1)).findAll();
    }

    @Test
    public void testFindOne(){
        when((facturaDetalleRepository.findById(1))).thenReturn(Optional.of(facturaDetalle));

        FacturaDetalle facturaDetalle = facturaDetalleService.findOne(1);

        assertNotNull(facturaDetalle);
        assertEquals(2, facturaDetalle.getCantidad());
        verify(facturaDetalleRepository, times(1)).findById(1);
    }

    @Test
    public void save(){
        when(facturaDetalleRepository.save(facturaDetalle)).thenReturn(facturaDetalle);
        FacturaDetalle facturaDetalle1 = facturaDetalleService.save(facturaDetalle);
        assertNotNull(facturaDetalle1);
        assertEquals(2, facturaDetalle.getCantidad());
        verify(facturaDetalleRepository, times(1)).save(facturaDetalle);
    }

    @Test
    public void update(){
        FacturaDetalle facturaDetalleActualizado = new FacturaDetalle(1, 4, 80.00, factura, libro);
        when(facturaDetalleRepository.findById(1)).thenReturn(Optional.of(facturaDetalle));
        when(facturaDetalleRepository.save(any(FacturaDetalle.class))).thenReturn(facturaDetalleActualizado);
        when(facturaRepository.findById(1)).thenReturn(Optional.of(factura));
        when(libroRepository.findById(1)).thenReturn(Optional.of(libro));
        FacturaDetalle facturaDetalle1 = facturaDetalleService.update(1,1,1,facturaDetalleActualizado);
        assertNotNull(facturaDetalle1);
        assertEquals(4, facturaDetalle1.getCantidad());
        assertEquals(80.00, facturaDetalle1.getSubtotal());
        verify(facturaDetalleRepository).save(any(FacturaDetalle.class));
    }

    @Test
    public void testDelete(){
        when(facturaDetalleRepository.existsById(1)).thenReturn(false);
        facturaDetalleService.delete(1);
        verify(facturaDetalleRepository, times(0)).deleteById(1);
    }
}
