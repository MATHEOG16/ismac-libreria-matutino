package com.distribuida.principal;

import com.distribuida.entities.Autor;

public class AutorPrincipal {

    public static void main(String[] args){

        Autor autor = new Autor(1, "Jose", "Alberto", "Ecuador", "AV.Algun lugar","0952447936", "autor@gmail.com");

        System.out.println(autor);
    }
}
