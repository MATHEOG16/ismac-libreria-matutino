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

        for (Categoria item: categorias){
            System.out.println(item.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<Categoria> categoria = categoriaRepository.findById(1);
        System.out.println(categoria.toString());
    }

    @Test
    public void save(){
        Categoria categoria = new Categoria();
    }
}
