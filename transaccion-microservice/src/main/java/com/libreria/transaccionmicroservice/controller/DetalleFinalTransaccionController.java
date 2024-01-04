package com.libreria.transaccionmicroservice.controller;
import com.libreria.transaccionmicroservice.entity.DetalleFInalTransaccion;
import com.libreria.transaccionmicroservice.service.DetalleFinalTransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/detalleTransaccion")
public class DetalleFinalTransaccionController {

    @Autowired
    DetalleFinalTransaccionService detalleFinalTransaccionService;

    @GetMapping
    public ResponseEntity<List<DetalleFInalTransaccion>> getAll(){

        List<DetalleFInalTransaccion> detalleFInalTransaccions = detalleFinalTransaccionService.getAll();
        if(detalleFInalTransaccions.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(detalleFInalTransaccions);
    }
    @GetMapping("/por-compra/{idCompra}")
    public List<DetalleFInalTransaccion> obtenerPorIdCompra(@PathVariable Long idCompra) {
        return detalleFinalTransaccionService.obtenerPorIdCompra(idCompra);
    }
    @GetMapping("/por-cliente/{idCliente}")
    public List<DetalleFInalTransaccion> obtenerPorIdCliente(@PathVariable Long idCliente){
        return detalleFinalTransaccionService.obtenerPorIdCliente(idCliente);
    }

}
