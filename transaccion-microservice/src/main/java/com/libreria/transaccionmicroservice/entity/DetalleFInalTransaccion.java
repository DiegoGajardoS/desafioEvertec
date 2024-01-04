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
public class DetalleFInalTransaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaCompra;
    private Long idCliente;
    private int precioCompra;

    private Long idCompra;
    public void setFechaCompra( Date fechaCompra){
        this.fechaCompra = fechaCompra;
    }
    public void setIdCliente (Long idCliente){
        this.idCliente = idCliente;
    }
    public void setPrecioCompra(int precioCompra){this.precioCompra = precioCompra;}

    public void setIdCompra(Long idCompra) { this.idCompra = idCompra;}

}
