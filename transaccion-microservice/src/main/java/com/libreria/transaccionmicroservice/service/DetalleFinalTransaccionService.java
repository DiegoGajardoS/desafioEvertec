package com.libreria.transaccionmicroservice.service;

import com.libreria.transaccionmicroservice.entity.DetalleFInalTransaccion;
import com.libreria.transaccionmicroservice.entity.Transaccion;
import com.libreria.transaccionmicroservice.repository.DetalleFinalTransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleFinalTransaccionService {

    @Autowired
    DetalleFinalTransaccionRepository detalleFinalTransaccionRepository;
    public List<DetalleFInalTransaccion> getAll(){
        return detalleFinalTransaccionRepository.findAll();
    }
    public List<DetalleFInalTransaccion> obtenerPorIdCompra(Long idCompra) {
        return detalleFinalTransaccionRepository.findByIdCompra(idCompra);
    }
    public List<DetalleFInalTransaccion> obtenerPorIdCliente(Long idCliente){
        return detalleFinalTransaccionRepository.findByIdCliente(idCliente);
    }
}
