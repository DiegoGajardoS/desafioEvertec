package com.libreria.transaccionmicroservice.service;

import com.libreria.transaccionmicroservice.dto.CompraRequestDTO;
import com.libreria.transaccionmicroservice.dto.DetalleCompraDTO;
import com.libreria.transaccionmicroservice.entity.Transaccion;
import com.libreria.transaccionmicroservice.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class TransaccionService {

    @Autowired
    TransaccionRepository transaccionRepository;


    public List<Transaccion> getAll(){
        return transaccionRepository.findAll();
    }
    public Transaccion getTransactionById(int id){
        return transaccionRepository.findById(id).orElse(null);
    }
    public Transaccion save(Transaccion transaccion){
        Transaccion transaccionNuevo = transaccionRepository.save(transaccion);
        return transaccionNuevo;
    }

    public Long getIdCompra(List<Transaccion> transacciones){
        Long id = 0L;
        if(transacciones.isEmpty()){
            return id;
        }
        else{
            for(Transaccion transaccion : transacciones){
                 Long idTransaccion = transaccion.getIdCompra();
                 id = Math.max(id,idTransaccion);
            }
            return id+1;
        }
       }

    public void realizarTransaccion(CompraRequestDTO compraRequestDTO){
        List<Transaccion> totalTransacciones = transaccionRepository.findAll();
        Long idCompra = getIdCompra(totalTransacciones);
        System.out.println("el id de compra es: " + idCompra);
        Date fechaCompra = compraRequestDTO.getTimestamp();
        System.out.println("la fecha de compra es: " + fechaCompra);
        List<DetalleCompraDTO> detallesCompraDTOS = compraRequestDTO.getDetallesCompraDTOS();
        Long idCliente = compraRequestDTO.getIdCliente();
        for(DetalleCompraDTO detalleCompraDTO : detallesCompraDTOS){
            Long idLibro = detalleCompraDTO.getIdLibro();
            int cantidad = detalleCompraDTO.getCantidad();
            Transaccion nuevaTransaccion = new Transaccion();
            nuevaTransaccion.setIdCompra(idCompra);
            nuevaTransaccion.setFechaCompra(fechaCompra);
            nuevaTransaccion.setCantidadLibro(cantidad);
            nuevaTransaccion.setIdLibro(idLibro);
            nuevaTransaccion.setIdCliente(idCliente);
            transaccionRepository.save(nuevaTransaccion);
        }

    }
}
