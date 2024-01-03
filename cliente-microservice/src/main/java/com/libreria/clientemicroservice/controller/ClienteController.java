package com.libreria.clientemicroservice.controller;


import com.libreria.clientemicroservice.entity.Cliente;
import com.libreria.clientemicroservice.feignClients.TransaccionFeignClient;
import com.libreria.clientemicroservice.model.CompraRequestDTO;
import com.libreria.clientemicroservice.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    TransaccionFeignClient transaccionFeignClient;


    @GetMapping
    public ResponseEntity<List<Cliente>> getAll(){
        List<Cliente> clientes = clienteService.getAll();
        if(clientes.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(clientes);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable("id") int id) {

        Cliente cliente = clienteService.getClienteById(id);
        if(cliente == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {

        Cliente clienteNuevo = clienteService.save(cliente);
        return ResponseEntity.ok(clienteNuevo);
    }

    @PostMapping("/realizarCompra/{idCliente}")
    public ResponseEntity<String> realizarTransaccionDesdeCliente(@PathVariable Long idCliente, @RequestBody CompraRequestDTO compraRequestDTO) {

        ResponseEntity<String> respuesta = clienteService.realizarTransaccionDesdeCliente(idCliente, compraRequestDTO);
        if (respuesta.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok("Solicitud de transacción enviada con éxito desde el cliente");
        } else {
            return ResponseEntity.status(respuesta.getStatusCode()).body("Error al enviar la solicitud de transacción desde el cliente");
        }
    }


}
