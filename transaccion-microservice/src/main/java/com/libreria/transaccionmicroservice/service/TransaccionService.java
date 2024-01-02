package com.libreria.transaccionmicroservice.service;

import com.libreria.transaccionmicroservice.dto.CompraRequestDTO;
import com.libreria.transaccionmicroservice.dto.DetalleCompraDTO;
import com.libreria.transaccionmicroservice.entity.Transaccion;
import com.libreria.transaccionmicroservice.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public void RealizarTransaccion(CompraRequestDTO compraRequestDTO){
        LocalDateTime fechaCompra = compraRequestDTO.getTimestamp();
        List<DetalleCompraDTO> detallesCompraDTOS = compraRequestDTO.getDetallesCompraDTOS();
        Long idCliente = compraRequestDTO.getIdCliente();
        for(DetalleCompraDTO detalleCompraDTO : detallesCompraDTOS){
            Long idLibro = detalleCompraDTO.getIdLibro();
            int cantidad = detalleCompraDTO.getCantidad();
            // crear metodo para obtener todas las transacciones y generar un id de compra mayor al ultimo registrado
        }

    }
}
