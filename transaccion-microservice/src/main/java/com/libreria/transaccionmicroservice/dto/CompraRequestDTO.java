package com.libreria.transaccionmicroservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CompraRequestDTO {

    private Long idCliente;
    private List<DetalleCompraDTO> detallesCompraDTOS;
    private LocalDateTime timestamp;



    public List<DetalleCompraDTO> getDetallesCompraDTOS() {
        return detallesCompraDTOS;
    }
    public LocalDateTime getTimestamp(){
        return timestamp;
    }

    public Long getIdCliente(){
        return idCliente;
    }
    public void setdetallesCompraDTOS(List<DetalleCompraDTO> detallesCompraDTOS) {
        this.detallesCompraDTOS = detallesCompraDTOS;
    }
    public void setTimestamp(LocalDateTime timestamp){
        this.timestamp = timestamp;
    }
    public void setIdCliente(Long idCliente){
        this.idCliente = idCliente;
    }
}
