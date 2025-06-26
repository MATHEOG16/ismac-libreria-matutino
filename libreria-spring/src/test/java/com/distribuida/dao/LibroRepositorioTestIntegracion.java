package com.distribuida.dao;

import com.distribuida.model.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class LibroRepositorioTestIntegracion {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Test
    public void findAll(){
        List<Libro> libros = libroRepository.findAll();

        for (Libro item: libros){
            System.out.println(item.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<Libro> libro = libroRepository.findById(1);

        System.out.println(libro.toString());
    }

    @Test
    public void save(){
        Libro libro = new Libro();

        Optional<Categoria> categoria = categoriaRepository.findById(1);
        Optional<Autor> autor = autorRepository.findById(1);

        libro.setIdLibro(0);
        libro.setTitulo("Oscuridad");
        libro.setEditorial("World");
        libro.setNumPaginas(300);
        libro.setEdicion("Primera");
        libro.setIdioma("Español");
        libro.setFechaPublicacion(new Date());
        libro.setDescripcion("Un mundo lleno de oscuridad");
        libro.setTipoPasta("Pasta dura");
        libro.setISBN("09008765432");
        libro.setNumEjemplares(10);
        libro.setPortada("Normal");
        libro.setPresentacion("Fisico y Digital");
        libro.setPrecio(30.50);
        libro.setCategoria(categoria.orElse(null));
        libro.setAutor(autor.orElse(null));

        libroRepository.save(libro);
    }

    @Test
    public void update(){
        Optional<Libro> libroExistente = libroRepository.findById(80);
        Optional<Categoria> categoria = categoriaRepository.findById(2);
        Optional<Autor> autor = autorRepository.findById(2);

        libroExistente.orElse(null).setPresentacion("Fisico");
        libroExistente.orElse(null).setNumEjemplares(30);
        libroExistente.orElse(null).setTipoPasta("Pasta dura con diseño especial");
        libroExistente.orElse(null).setPrecio(35.00);
        libroExistente.orElse(null).setPortada("Aniversario");
        libroExistente.orElse(null).setCategoria(categoria.orElse(null));
        libroExistente.orElse(null).setAutor(autor.orElse(null));


        libroRepository.save(libroExistente.orElse(null));
    }

    @Test
    public void delete(){
        if (libroRepository.existsById(79)){
            libroRepository.deleteById(79);
        }
    }
}
