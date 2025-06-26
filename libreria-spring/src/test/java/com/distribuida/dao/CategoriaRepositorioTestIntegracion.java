package com.distribuida.dao;
import com.distribuida.model.Categoria;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class CategoriaRepositorioTestIntegracion {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    public void findAll(){
        List<Categoria> categorias = categoriaRepository.findAll();
        assertNotNull(categorias);
        assertTrue(categorias.size() > 0);
        for (Categoria item: categorias){
            System.out.println(item.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<Categoria> categoria = categoriaRepository.findById(1);
        assertTrue(categoria.isPresent(), "La categoria con id= 1, deberia existir");
        System.out.println(categoria.toString());
    }

    @Test
    public void save(){
        Categoria categoria = new Categoria(0, "Romance", "El amor esta en el aire");
        categoriaRepository.save(categoria);
        assertNotNull(categoria.getIdCategoria(), "La categoria guardada debe tener un id.");
        assertEquals("Romance", categoria.getCategoria());
        assertEquals("El amor esta en el aire", categoria.getDescripcion());
    }

    @Test
    public void update() {

        Categoria nuevaCategoria = new Categoria(0, "Drama", "Historias intensas");
        nuevaCategoria = categoriaRepository.save(nuevaCategoria);

        Optional<Categoria> categoria = categoriaRepository.findById(nuevaCategoria.getIdCategoria());
        assertTrue(categoria.isPresent(), "La categoría recién guardada debe existir");

        categoria.get().setCategoria("Terror");
        categoria.get().setDescripcion("El terror está en el aire");

        Categoria categoriaActualizada = categoriaRepository.save(categoria.get());

        assertEquals("Terror", categoriaActualizada.getCategoria());
        assertEquals("El terror está en el aire", categoriaActualizada.getDescripcion());
    }


    @Test
    public void delete(){
        if (categoriaRepository.existsById(58)) {
            categoriaRepository.deleteById(58);
        }
        assertFalse(categoriaRepository.existsById(58), "El id= 58 deberia haberse eliminado");
    }

}
