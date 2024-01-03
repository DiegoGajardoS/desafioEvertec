package com.libreria.clientemicroservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleCompraDTO {

    private Long idLibro;
    private int cantidad;

    public DetalleCompraDTO(Long idLibro, int cantidad){
        this.idLibro = idLibro;
        this.cantidad = cantidad;
    }

    public Long getIdLibro(){
        return idLibro;
    }
    public int getCantidad(){
        return cantidad;
    }
}
