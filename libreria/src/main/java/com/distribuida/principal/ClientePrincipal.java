package com.distribuida.principal;

import com.distribuida.entities.Cliente;

public class ClientePrincipal {

    public static void main(String[] args){

        Cliente cliente = new Cliente(1, "1589357895", "Jaime", "Lopez", "AV. Infinito y mas alla", "0987664102", "correo@gmail.com" );

        System.out.println(cliente.toString());
    }
}
