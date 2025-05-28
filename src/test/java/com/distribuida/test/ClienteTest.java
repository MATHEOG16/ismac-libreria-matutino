package com.distribuida.test;
import com.distribuida.entities.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {
    
    private Cliente cliente;

    @BeforeEach
    public void setup(){cliente = new Cliente(1,"1788430215", "Karlos", "Taipe", "Av. infinito y mas alla", "0975441236", "correo@gmail.com");
    }

    @Test
    public void testClienteConstructorAndGetters(){
        assertAll("Validar datos Cliente, Constructor y Getters",
                () -> assertEquals(1, cliente.getIdCliente()),
                () -> assertEquals("1788430215", cliente.getCedula()),
                () -> assertEquals("Karlos", cliente.getNombre()),
                () -> assertEquals("Taipe", cliente.getApellido()),
                () -> assertEquals("Av. infinito y mas alla", cliente.getDireccion()),
                () -> assertEquals("0975441236", cliente.getTelefono()),
                () -> assertEquals("correo@gmail.com", cliente.getCorreo())
        );
    }

    @Test
    public void testClienteSetters(){
        cliente.setIdCliente(2);
        cliente.setCedula("1788430212");
        cliente.setNombre("Karlos2");
        cliente.setApellido("Taipe2");
        cliente.setDireccion("Av. infinito y mas alla2");
        cliente.setTelefono("0975441232");
        cliente.setCorreo("correo2@gmail.com");
        assertAll("Validar Datos Cliente - Setters",
                () -> assertEquals(2, cliente.getIdCliente()),
                () -> assertEquals("1788430212", cliente.getCedula()),
                () -> assertEquals("Karlos2", cliente.getNombre()),
                () -> assertEquals("Taipe2", cliente.getApellido()),
                () -> assertEquals("Av. infinito y mas alla2", cliente.getDireccion()),
                () -> assertEquals("0975441232", cliente.getTelefono()),
                () -> assertEquals("correo2@gmail.com", cliente.getCorreo())
        );
    }

    @Test
    public void testClienteToString(){
        String str = cliente.toString();
        assertAll("Validar Datos Clientes toString",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("1788430215")),
                () -> assertTrue(str.contains("Karlos")),
                () -> assertTrue(str.contains("Taipe")),
                () -> assertTrue(str.contains("Av. infinito y mas alla")),
                () -> assertTrue(str.contains("0975441236")),
                () -> assertTrue(str.contains("correo@gmail.com"))
        );
    }
}
