package com.distribuida.service;

import com.distribuida.dao.ReseñaRepository;
import com.distribuida.model.Libro;
import com.distribuida.model.Reseña;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReseñaServiceImplTest {

    @Mock
    private ReseñaRepository reseñaRepository;

    @InjectMocks
    private ReseñaServiceImpl reseñaService;

    private Libro libro;
    private Reseña reseña;

    @BeforeEach
    void setUp() {
        libro = new Libro();
        libro.setIdLibro(1); // Usa el setter correcto para tu clase Libro

        reseña = new Reseña();
        reseña.setId(1L);
        reseña.setComentario("Muy buen libro");
        reseña.setCalificacion(5);
        reseña.setLibro(libro);
    }

    @Test
    void crearReseña_deberiaGuardarYRetornarReseña() {
        when(reseñaRepository.save(reseña)).thenReturn(reseña);

        Reseña resultado = reseñaService.crearReseña(reseña);

        assertNotNull(resultado);
        assertEquals("Muy buen libro", resultado.getComentario());
        assertEquals(5, resultado.getCalificacion());
        assertEquals(libro, resultado.getLibro());
        verify(reseñaRepository, times(1)).save(reseña);
    }

    @Test
    void listarReseñasPorLibro_deberiaRetornarLista() {
        List<Reseña> reseñas = Arrays.asList(reseña);
        when(reseñaRepository.findByLibroId(1L)).thenReturn(reseñas);

        List<Reseña> resultado = reseñaService.listarReseñasPorLibro(1L);

        assertEquals(1, resultado.size());
        assertEquals("Muy buen libro", resultado.get(0).getComentario());
        verify(reseñaRepository, times(1)).findByLibroId(1L);
    }
}
