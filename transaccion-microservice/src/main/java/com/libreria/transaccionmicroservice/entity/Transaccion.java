package com.libreria.transaccionmicroservice.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaCompra;
    private Long idCliente;
    private Long idLibro;
    private int cantidadLibro;
    private Long idCompra;

    public Long getIdCompra(){
        return idCompra;
    }

    public void setFechaCompra( Date fechaCompra){
        this.fechaCompra = fechaCompra;
    }
    public void setIdCliente (Long idCliente){
        this.idCliente = idCliente;
    }
    public void setIdLibro (Long idLibro){
        this.idLibro = idLibro;
    }
    public void setCantidadLibro (int cantidadLibro){
        this.cantidadLibro = cantidadLibro;
    }
    public void setIdCompra (Long idCompra){
        this.idCompra = idCompra;
    }
}
