package com.distribuida.principal;

import com.distribuida.entities.Cliente;
import com.distribuida.entities.Factura;

import java.util.Date;

public class FacturaPrincipal {

    public static void main(String[] args){

        Factura factura = new Factura();

        Cliente cliente = new Cliente(1, "1589357895", "Jaime", "Lopez", "AV. Infinito y mas alla", "0987664102", "correo@gmail.com" );

        factura.setIdFactura(1);
        factura.setNumFactura("Fac-0001");
        factura.setFecha(new Date());
        factura.setTotalNeto(100.00);
        factura.setIva(15.00);
        factura.setTotal(115.00);
        // inyeccion de dependencias
        factura.setCliente(cliente);

        System.out.println(factura.toString());

    }

}
