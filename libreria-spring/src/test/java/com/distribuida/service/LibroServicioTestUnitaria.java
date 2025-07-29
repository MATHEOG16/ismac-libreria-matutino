package com.distribuida.service;

import com.distribuida.dao.AutorRepository;
import com.distribuida.dao.CategoriaRepository;
import com.distribuida.dao.LibroRepository;
import com.distribuida.model.Autor;
import com.distribuida.model.Categoria;
import com.distribuida.model.Libro;
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
public class LibroServicioTestUnitaria {

    @Mock
    private LibroRepository libroRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private LibroServiceImpl libroService;

    @InjectMocks
    private CategoriaServiceImpl categoriaService;

    @InjectMocks
    private AutorServiceImpl autorService;

    private Libro libro;
    private Categoria categoria;
    private Autor autor;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        categoria = new Categoria(1, "Romance", "El amor esta en el aire");
        autor = new Autor(1, "Forencio", "Castro", "Ecuador", "Av. Ahi", "0985332586", "autor@gmail.com");
        libro = new Libro(1,"El final", "Twil", 200, "Aniversario", "Español", new Date(),"Un cataclismo se dio en el mundo", "Pasta dura", "054866255", 20, "Especial", "Fisico", 30.00, categoria, autor);

    }

    @Test
    public void testFindAll(){
        when(libroRepository.findAll()).thenReturn(Arrays.asList(libro));
        List<Libro> libros = libroService.findAll();
        assertNotNull(libros);
        assertEquals(1, libros.size());
        verify(libroRepository, times(1)).findAll();
    }

    @Test
    public void testFindOne(){
        when((libroRepository.findById(1))).thenReturn(Optional.of(libro));

        Libro libro = libroService.findOne(1);

        assertNotNull(libro);
        assertEquals("El final", libro.getTitulo());
        verify(libroRepository, times(1)).findById(1);
    }

    @Test
    public void save(){
        when(libroRepository.save(libro)).thenReturn(libro);
        Libro libro1 = libroService.save(libro);
        assertNotNull(libro1);
        assertEquals("El final", libro.getTitulo());
        verify(libroRepository, times(1)).save(libro);
    }

    @Test
    public void update(){
        Libro libroActualizado = new Libro(1, "Te encontrare", "Twil", 200, "Aniversario", "Español",  new Date(),"El buscara venganza", "Pasta dura", "054866255", 20, "Especial", "Fisico", 30.00, categoria, autor);
        when(libroRepository.findById(1)).thenReturn(Optional.of(libro));
        when(libroRepository.save(any(Libro.class))).thenReturn(libroActualizado);
        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));
        when(autorRepository.findById(1)).thenReturn(Optional.of(autor));
        Libro libro1 = libroService.update(1,1,1,libroActualizado);
        assertNotNull(libro1);
        assertEquals("Te encontrare", libro1.getTitulo());
        assertEquals("El buscara venganza", libro1.getDescripcion());
        verify(libroRepository).save(any(Libro.class));
    }

    @Test
    public void testDelete(){
        when(libroRepository.existsById(1)).thenReturn(false);
        libroService.delete(1);
        verify(libroRepository, times(0)).deleteById(1);
    }
}
