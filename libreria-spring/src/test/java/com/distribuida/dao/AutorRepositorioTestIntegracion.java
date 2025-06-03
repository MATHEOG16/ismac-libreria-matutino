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
    public void update(){
        Optional<Autor> autor = autorRepository.findById(54);

        assertTrue(autor.isPresent(), "El autor con id= 54 debe de existir para ser actualizado");

        autor.orElse(null).setPais("Chile");
        autor.orElse(null).setNombre("Jose2");
        autor.orElse(null).setApellido("Alberto2");

        Autor autorActualizado = autorRepository.save(autor.orElse(null));

        assertEquals("Jose2", autorActualizado.getNombre());
        assertEquals("Alberto2", autorActualizado.getApellido());
    }

    @Test
    public void delete(){
        if (autorRepository.existsById(54)){
            autorRepository.deleteById(54);
        }
        assertFalse(autorRepository.existsById(54), "El id= 54 deberia haberse eliminado");
    }
}
