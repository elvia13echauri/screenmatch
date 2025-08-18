package com.aluracursos.screenmatch.model;

import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

public enum Categoria {

    ACCION("Action"),
    ROMANCE("Romance"),
    COMEDIA("Comedy"),
    DRAMA("Drama"),
    CRIMEN("Crime");

    private String categoriaOmdb;

    Categoria(String categoriaOmdb){
        this.categoriaOmdb = categoriaOmdb;
    }

}
