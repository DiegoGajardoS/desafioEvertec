package com.libreria.transaccionmicroservice.controller;


import com.libreria.transaccionmicroservice.dto.CompraRequestDTO;
import com.libreria.transaccionmicroservice.entity.Transaccion;
import com.libreria.transaccionmicroservice.service.TransaccionService;
import com.libreria.transaccionmicroservice.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/transaccion")
public class TransaccionController {

    @Autowired
    TransaccionService transaccionService;

    @GetMapping
    public ResponseEntity<List<Transaccion>> getAll(){

        List<Transaccion> transacciones = transaccionService.getAll();
        if(transacciones.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(transacciones);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Transaccion> getById(@PathVariable("id") int id) {

        Transaccion transaccion = transaccionService.getTransactionById(id);
        if(transaccion == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(transaccion);
    }

    @PostMapping
    public ResponseEntity<Transaccion> save(@RequestBody Transaccion transaccion) {

        Transaccion transaccionNuevo = transaccionService.save(transaccion);
        return ResponseEntity.ok(transaccionNuevo);
    }

    @PostMapping("/realizar")
    public ResponseEntity<String> realizarCompra(@RequestBody CompraRequestDTO compraRequestDTO) {
        compraRequestDTO.setTimestamp(LocalDateTime.now());
        transaccionService.RealizarTransaccion(compraRequestDTO);
        return ResponseEntity.ok("Compra realizada con éxito");
    }
}
