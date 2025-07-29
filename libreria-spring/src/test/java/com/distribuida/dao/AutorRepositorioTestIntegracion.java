package com.distribuida.dao;

import com.distribuida.model.Autor;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class AutorRepositorioTestIntegracion {

    @Autowired
    private AutorRepository autorRepository;

    @Test
    public void findAll(){
        List<Autor> autors = autorRepository.findAll();
        assertNotNull(autors);
        assertTrue(autors.size() > 0);
        for (Autor item: autors){
            System.out.println(item.toString());
        }

    }

    @Test
    public void findOne(){
        Optional<Autor> cliente = autorRepository.findById(1);
        assertTrue(cliente.isPresent(), "El autor con id= 1, deberia existir");
        System.out.println(cliente.toString());
    }

    @Test
    public void save(){
        Autor autor = new Autor(0, "Jose", "Alberto", "Ecuador", "AV.Algun lugar","0952447936", "autor@gmail.com");
        autorRepository.save(autor);
        assertNotNull(autor.getIdAutor(), "El autor guardado debe tener un id.");
        assertEquals("Ecuador", autor.getPais());
        assertEquals("Jose", autor.getNombre());
    }

    @Test
    public void update() {

        Autor autorNuevo = new Autor(0, "Carlos", "Lopez", "Perú", "Av. Siempre Viva", "0999999999", "test@correo.com");
        autorNuevo = autorRepository.save(autorNuevo); // persistir y recuperar ID

        Optional<Autor> autor = autorRepository.findById(autorNuevo.getIdAutor());
        assertTrue(autor.isPresent(), "El autor recién guardado debe existir");

        autor.get().setNombre("Jose2");
        autor.get().setApellido("Alberto2");
        autor.get().setPais("Chile");

        Autor autorActualizado = autorRepository.save(autor.get());

        assertEquals("Jose2", autorActualizado.getNombre());
        assertEquals("Alberto2", autorActualizado.getApellido());
        assertEquals("Chile", autorActualizado.getPais());
    }


    @Test
    public void delete(){
        if (autorRepository.existsById(55)){
            autorRepository.deleteById(55);
        }
        assertFalse(autorRepository.existsById(55), "El id= 54 deberia haberse eliminado");
    }
}
