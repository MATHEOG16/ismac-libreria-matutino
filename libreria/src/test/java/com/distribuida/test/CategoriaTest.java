package com.distribuida.test;

import static org.junit.jupiter.api.Assertions.*;

import com.distribuida.entities.Categoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategoriaTest {

    private Categoria categoria;

    @BeforeEach
    public void setup() {
        categoria = new Categoria(1, "Romance", "El amor esta en el aire");
    }

    @Test
    public void testCategoriaConstructorAndGetters() {
        assertAll("Validar datos de Categoria - Constructor y Getters",
                () -> assertEquals(1, categoria.getIdCategoria()),
                () -> assertEquals("Romance", categoria.getCategoria()),
                () -> assertEquals("El amor esta en el aire", categoria.getDescripcion())
        );
    }

    @Test
    public void testCategoriaSetters() {
        categoria.setIdCategoria(2);
        categoria.setCategoria("Terror");
        categoria.setDescripcion("Historias que dan miedo");

        assertAll("Validar datos de Categoria - Setters",
                () -> assertEquals(2, categoria.getIdCategoria()),
                () -> assertEquals("Terror", categoria.getCategoria()),
                () -> assertEquals("Historias que dan miedo", categoria.getDescripcion())
        );
    }

    @Test
    public void testCategoriaToString(){
        String str = categoria.toString();
        assertAll("Validar Datos Categoria toString",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Romance")),
                () -> assertTrue(str.contains("El amor esta en el aire"))
        );
    }
}

