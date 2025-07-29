package com.distribuida.dao;

import com.distribuida.model.Cliente;
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
public class ClienteRepositorioTestIntegracion {

    @Autowired // anotacion para inyectar dependencias
    private ClienteRepository clienteRepository;

    @Test
    public void findAll(){
        List<Cliente> clientes = clienteRepository.findAll();
        assertNotNull(clientes);
        assertTrue(clientes.size() > 0);
        for (Cliente item: clientes){
            System.out.println(item.toString());
        }

    }

    @Test
    public void findOne(){
        Optional<Cliente> cliente = clienteRepository.findById(1);
        assertTrue(cliente.isPresent(), "El cliente con id= 1, deberia existir");
        System.out.println(cliente.toString());
    }

    @Test
    public void save(){
        Cliente cliente = new Cliente(0, "1589357895", "Jaime", "Lopez", "AV. Infinito y mas alla", "0987664102", "correo@gmail.com" );
        clienteRepository.save(cliente);
        assertNotNull(cliente.getIdCliente(), "El cliente guardado debe tener un id.");
        assertEquals("1589357895", cliente.getCedula());
        assertEquals("Jaime", cliente.getNombre());
    }

    @Test
    public void update(){
        Optional<Cliente> cliente = clienteRepository.findById(1);

        assertTrue(cliente.isPresent(), "El cliente con id= 40 debe de existir para ser actualizado");

        cliente.orElse(null).setCedula("2222222222");
        cliente.orElse(null).setNombre("Jaime2");
        cliente.orElse(null).setApellido("Lopez2");

        Cliente clienteActualizado = clienteRepository.save(cliente.orElse(null));

        assertEquals("Jaime2", clienteActualizado.getNombre());
        assertEquals("Lopez2", clienteActualizado.getApellido());
    }

    @Test
    public void delete(){
        if (clienteRepository.existsById(39)){
            clienteRepository.deleteById(39);
        }
        assertFalse(clienteRepository.existsById(39), "El id= 39 deberia haberse eliminado");
    }
}
