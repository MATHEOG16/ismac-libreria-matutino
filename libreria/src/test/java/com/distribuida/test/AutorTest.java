package com.distribuida.test;

import com.distribuida.entities.Autor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AutorTest {

    private Autor autor;

    @BeforeEach
    public void setup(){autor = new Autor(1,"Jose", "Alberto", "Ecuador", "AV.Algun lugar", "0952447936", "autor@gmail.com");
    }

    @Test
    public void testAutorConstructorAndGetters() {
        assertAll("Validar datos Autor, Constructor y Getters",
                () -> assertEquals(1, autor.getIdAutor()),
                () -> assertEquals("Jose", autor.getNombre()),
                () -> assertEquals("Alberto", autor.getApellido()),
                () -> assertEquals("Ecuador", autor.getPais()),
                () -> assertEquals("AV.Algun lugar", autor.getDireccion()),
                () -> assertEquals("0952447936", autor.getTelefono()),
                () -> assertEquals("autor@gmail.com", autor.getCorreo())
        );
    }

    @Test
    public void testAutorSetters() {
        autor.setIdAutor(2);
        autor.setNombre("Jose2");
        autor.setApellido("Alberto2");
        autor.setPais("Chile");
        autor.setDireccion("AV.Algun lugar2");
        autor.setTelefono("0932447936");
        autor.setCorreo("autor2@gmail.com");

        assertAll("Validar Datos Autor - Setters",
                () -> assertEquals(2, autor.getIdAutor()),
                () -> assertEquals("Jose2", autor.getNombre()),
                () -> assertEquals("Alberto2", autor.getApellido()),
                () -> assertEquals("Chile", autor.getPais()),
                () -> assertEquals("AV.Algun lugar2", autor.getDireccion()),
                () -> assertEquals("0932447936", autor.getTelefono()),
                () -> assertEquals("autor2@gmail.com", autor.getCorreo())
        );
    }


    @Test
    public void testAutorToString(){
        String str = autor.toString();
        assertAll("Validar Datos Autor toString",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Jose")),
                () -> assertTrue(str.contains("Alberto")),
                () -> assertTrue(str.contains("Ecuador")),
                () -> assertTrue(str.contains("AV.Algun lugar")),
                () -> assertTrue(str.contains("0952447936")),
                () -> assertTrue(str.contains("autor@gmail.com"))
        );
    }

}
