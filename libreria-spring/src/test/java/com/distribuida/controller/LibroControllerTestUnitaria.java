package com.distribuida.controller;

import com.distribuida.model.Autor;
import com.distribuida.model.Categoria;
import com.distribuida.model.Libro;
import com.distribuida.service.LibroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

public class LibroControllerTestUnitaria {

    @InjectMocks
    private LibroController libroController;

    @Mock
    private LibroService libroService;

    private Libro libro;
    private Categoria categoria;
    private Autor autor;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        libro = new Libro();
        libro.setIdLibro(1);
        libro.setTitulo("El final");
        libro.setEditorial("Twil");
        libro.setNumPaginas(200);
        libro.setEdicion("Aniversario");
        libro.setIdioma("Español");
        libro.setFechaPublicacion(new Date());
        libro.setDescripcion("Un cataclismo se dio en el mundo");
        libro.setTipoPasta("Pasta dura");
        libro.setISBN("054866255");
        libro.setNumEjemplares(20);
        libro.setPortada("Especial");
        libro.setPresentacion("Fisico");
        libro.setPrecio(30.00);
        libro.setCategoria(new Categoria());
        libro.setAutor(new Autor());
    }

    @Test
    public void testFindAll(){
        when(libroService.findAll()).thenReturn(List.of(libro));
        ResponseEntity<List<Libro>> respuesta = libroController.findAll();
        assertEquals(200,respuesta.getStatusCodeValue());
        assertEquals(1,respuesta.getBody().size());
        verify(libroService, times(1)).findAll();
    }

    @Test
    public void testFindOne(){
        when(libroService.findOne(1)).thenReturn(libro);
        ResponseEntity<Libro> respuesta = libroController.findOne(1);
        assertEquals(200,respuesta.getStatusCodeValue());
        assertEquals(libro.getTitulo(),respuesta.getBody().getTitulo());
    }

    @Test
    public void testOneNoExistente(){
        when(libroService.findOne(2)).thenReturn(null);
        ResponseEntity<Libro> respuesta = libroController.findOne(2);
        assertEquals(200, respuesta.getStatusCodeValue());
    }

    @Test
    public void testSave(){
        when(libroService.save(any(Libro.class))).thenReturn(libro);
        ResponseEntity<Libro> respuesta = libroController.save(libro);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("El final", respuesta.getBody().getTitulo());
    }

    @Test
    public void testUpdateExistente(){
        when(libroService.update(
                eq(1),
                eq(1),
                eq(1),
                any(Libro.class)
        )).thenReturn(libro);
        ResponseEntity<Libro> respuesta = libroController.update(1,1,1, libro);
        assertEquals(200, respuesta.getStatusCodeValue());
    }

    @Test
    public void testUpdateNoExistente(){
        when(libroService.update(eq(1), eq(1), eq(1),any(Libro.class))).thenReturn(null);
        ResponseEntity<Libro> respuesta = libroController.update(2,2,2, libro);
        assertEquals(404, respuesta.getStatusCodeValue());
    }

    @Test
    public void testDelete(){
        doNothing().when(libroService).delete(1);
        ResponseEntity<Void> respuesta = libroController.delete(1);
        assertEquals(204, respuesta.getStatusCodeValue());
        verify(libroService,times(1)).delete(1);
    }
}
