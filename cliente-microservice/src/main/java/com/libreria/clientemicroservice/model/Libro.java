package com.libreria.clientemicroservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Libro {

    private int id;
    private String titulo;
    private int stock;
    private String autor;
    private String genero;
    private String editorial;
    private int precio;

    public int getStock(){ return stock;}
    public int getPrecio(){ return precio;}
}
