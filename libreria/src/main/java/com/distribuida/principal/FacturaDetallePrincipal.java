package com.distribuida.principal;

import com.distribuida.entities.FacturaDetalle;
import com.distribuida.entities.Factura;
import com.distribuida.entities.Libro;
import com.distribuida.entities.Cliente;
import com.distribuida.entities.Categoria;
import com.distribuida.entities.Autor;



import java.time.LocalDate;
import java.util.Date;

public class FacturaDetallePrincipal {

    public static void main(String[] args){

        FacturaDetalle facturaDetalle = new FacturaDetalle();
        Factura factura = new Factura();
        Libro libro = new Libro();
        Cliente cliente = new Cliente(1, "1589357895", "Jaime", "Lopez", "AV. Infinito y mas alla", "0987664102", "correo@gmail.com" );
        Categoria categoria = new Categoria(1, "Romance", "El amor esta en el aire");
        Autor autor = new Autor(1, "Jose", "Alberto", "Ecuador", "AV.Algun lugar","0952447936", "autor@gmail.com");




        facturaDetalle.setCantidad(2);
        facturaDetalle.setSubtotal(40.00);
        facturaDetalle.setFactura(factura);
        facturaDetalle.setLibro(libro);

        factura.setIdFactura(1);
        factura.setNumFactura("Fac-0001");
        factura.setFecha(new Date());
        factura.setTotalNeto(100.00);
        factura.setIva(15.00);
        factura.setTotal(115.00);
        // inyeccion de dependencias
        factura.setCliente(cliente);

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

        System.out.println(facturaDetalle);

    }
}
