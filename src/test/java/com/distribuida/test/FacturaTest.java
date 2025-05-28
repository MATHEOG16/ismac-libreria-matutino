package com.distribuida.test;
import com.distribuida.entities.Cliente;
import com.distribuida.entities.Factura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FacturaTest {

    private Factura factura;

    @BeforeEach
    public void setUp() {
        // Crear una fecha: 15 de mayo de 2025
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.MAY, 15); // Los meses empiezan desde 0
        Date fecha = calendar.getTime();

        // Crear un cliente de ejemplo (puedes ajustarlo según tu clase Cliente)
        Cliente cliente = new Cliente(); // Suponiendo que existe un constructor por defecto

        factura = new Factura(1, "Fac-0001", fecha, 100.00, 15.00, 115.00, cliente);
    }


    @Test
    public void testFacturaConstructorAndGetters() {
        Calendar expectedCalendar = Calendar.getInstance();
        expectedCalendar.set(2025, Calendar.MAY, 15);
        Date expectedDate = expectedCalendar.getTime();

        assertAll("Validar datos Factura, Constructor y Getters",
                () -> assertEquals(1, factura.getIdFactura()),
                () -> assertEquals("Fac-0001", factura.getNumFactura()),
                () -> assertEquals(expectedDate, factura.getFecha()),
                () -> assertEquals(100.00, factura.getTotalNeto()),
                () -> assertEquals(15.00, factura.getIva()),
                () -> assertEquals(115.00, factura.getTotal()),
                () -> assertNotNull(factura.getCliente())
        );
    }

    @Test
    public void testFacturaSetters(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.MAY, 15);
        Date fecha = calendar.getTime();
        Cliente cliente = new Cliente(); // Cliente de prueba
        Factura factura = new Factura();

        factura.setIdFactura(1);
        factura.setNumFactura("Fac-0001");
        factura.setFecha(fecha);
        factura.setTotalNeto(100.00);
        factura.setIva(15.00);
        factura.setTotal(115.00);
        factura.setCliente(cliente);

        assertEquals(1, factura.getIdFactura());
        assertEquals("Fac-0001", factura.getNumFactura());
        assertEquals(fecha, factura.getFecha());
        assertEquals(100.00, factura.getTotalNeto());
        assertEquals(15.00, factura.getIva());
        assertEquals(115.00, factura.getTotal());
        assertEquals(cliente, factura.getCliente()

        );
    }

    @Test
    public void testFacturaToString() {
        // Crear fecha
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.MAY, 15);
        Date fecha = calendar.getTime();

        // Crear cliente de prueba
        Cliente cliente = new Cliente(); // Asegúrate de que Cliente tenga un toString definido también

        // Crear factura
        Factura factura = new Factura(1, "Fac-0001", fecha, 100.00, 15.00, 115.00, cliente);

        String facturaStr = factura.toString();

        // Verificar que la cadena contenga los valores esperados
        assertAll("Validar método toString de Factura",
                () -> assertTrue(facturaStr.contains("idFactura=1")),
                () -> assertTrue(facturaStr.contains("numFactura='Fac-0001'")),
                () -> assertTrue(facturaStr.contains("fecha=")), // No comprobamos la fecha exacta para evitar errores de formato
                () -> assertTrue(facturaStr.contains("totalNeto=100.0")),
                () -> assertTrue(facturaStr.contains("iva=15.0")),
                () -> assertTrue(facturaStr.contains("total=115.0")),
                () -> assertTrue(facturaStr.contains("cliente=")) // Aquí depende de cómo esté definido el toString de Cliente
        );
    }
}
