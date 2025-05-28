package com.distribuida.test;

import static org.junit.jupiter.api.Assertions.*;

import com.distribuida.entities.Autor;
import com.distribuida.entities.Categoria;
import com.distribuida.entities.Libro;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class LibroTest {

    @Test
    public void testLibroSettersAndGetters() {
        Libro libro = new Libro();
        Categoria categoria = new Categoria(1, "Romance", "El amor esta en el aire");
        Autor autor = new Autor(1, "Jose", "Alberto", "Ecuador", "AV.Algun lugar", "0952447936", "autor@gmail.com");

        libro.setIdLibro(2);
        libro.setTitulo("Hercules");
        libro.setEditorial("Odis");
        libro.setNumPaginas(200);
        libro.setEdicion("Edit");
        libro.setIdioma("Español");
        libro.setFechaPublicacion(LocalDate.of(2000, 2, 10));
        libro.setDescripcion("Semidios");
        libro.setTipoPasta("Gruesa");
        libro.setISBN("DN");
        libro.setNumEjemplares(100);
        libro.setPortada("Lisa");
        libro.setPresentacion("TV");
        libro.setPrecio(20.00);
        libro.setCategoria(categoria);
        libro.setAutor(autor);

        assertAll("Validar datos del Libro",
                () -> assertEquals(2, libro.getIdLibro()),
                () -> assertEquals("Hercules", libro.getTitulo()),
                () -> assertEquals("Odis", libro.getEditorial()),
                () -> assertEquals(200, libro.getNumPaginas()),
                () -> assertEquals("Edit", libro.getEdicion()),
                () -> assertEquals("Español", libro.getIdioma()),
                () -> assertEquals(LocalDate.of(2000, 2, 10), libro.getFechaPublicacion()),
                () -> assertEquals("Semidios", libro.getDescripcion()),
                () -> assertEquals("Gruesa", libro.getTipoPasta()),
                () -> assertEquals("DN", libro.getISBN()),
                () -> assertEquals(100, libro.getNumEjemplares()),
                () -> assertEquals("Lisa", libro.getPortada()),
                () -> assertEquals("TV", libro.getPresentacion()),
                () -> assertEquals(20.00, libro.getPrecio()),

                // Validar categoría
                () -> assertNotNull(libro.getCategoria()),
                () -> assertEquals(1, libro.getCategoria().getIdCategoria()),
                () -> assertEquals("Romance", libro.getCategoria().getCategoria()),
                () -> assertEquals("El amor esta en el aire", libro.getCategoria().getDescripcion()),

                // Validar autor
                () -> assertNotNull(libro.getAutor()),
                () -> assertEquals(1, libro.getAutor().getIdAutor()),
                () -> assertEquals("Jose", libro.getAutor().getNombre()),
                () -> assertEquals("Alberto", libro.getAutor().getApellido()),
                () -> assertEquals("Ecuador", libro.getAutor().getPais()),
                () -> assertEquals("AV.Algun lugar", libro.getAutor().getDireccion()),
                () -> assertEquals("0952447936", libro.getAutor().getTelefono()),
                () -> assertEquals("autor@gmail.com", libro.getAutor().getCorreo())
        );
    }
}

