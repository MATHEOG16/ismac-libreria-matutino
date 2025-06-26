package com.distribuida.principal;
import com.distribuida.entities.Libro;
import com.distribuida.entities.Categoria;
import com.distribuida.entities.Autor;

import java.time.LocalDate;

public class LibroPrincipal {

    public static void main(String[] args){

        Libro libro = new Libro();
        Categoria categoria = new Categoria(1, "Romance", "El amor esta en el aire");
        Autor autor = new Autor(1, "Jose", "Alberto", "Ecuador", "AV.Algun lugar","0952447936", "autor@gmail.com");

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

        System.out.println(libro);
    }
}
