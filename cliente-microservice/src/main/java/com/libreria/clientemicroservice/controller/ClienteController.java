package com.libreria.clientemicroservice.controller;


import com.libreria.clientemicroservice.entity.Cliente;
import com.libreria.clientemicroservice.feignClients.TransaccionFeignClient;
import com.libreria.clientemicroservice.model.CompraRequestDTO;
import com.libreria.clientemicroservice.model.DetalleFinalTransaccion;
import com.libreria.clientemicroservice.model.Libro;
import com.libreria.clientemicroservice.model.Transaccion;
import com.libreria.clientemicroservice.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    @GetMapping("buscarLibro/{idLibro}")
    public ResponseEntity<Libro> obtenerLibro(@PathVariable int idLibro){
        Libro libro = clienteService.obtenerLibro(idLibro);
        return ResponseEntity.ok(libro);
    }

    @PutMapping("/actualizarStockDelLibro/{idLibro}")
    public void actualizarStockDelLibro(@PathVariable int idLibro,@RequestBody int nuevoStock) {
        clienteService.actualizarStockDelLibro(idLibro, nuevoStock);
    }

    @GetMapping("/transacciones")
    public ResponseEntity<List<Transaccion>> obtenerTodasLasTransacciones() {
        List<Transaccion> transacciones = clienteService.obtenerTodasLasTransacciones();
        return ResponseEntity.ok(transacciones);
    }

    @GetMapping("/transacciones/compra/{idCompra}")
    public ResponseEntity<List<Transaccion>> obtenerTransaccionesPorIdCompra(@PathVariable Long idCompra) {
        List<Transaccion> transaccions = clienteService.obtenerTransaccionesPorIdCompra(idCompra);
        return ResponseEntity.ok(transaccions);
    }
    @GetMapping("/transacciones/cliente/{idCliente}")
    public ResponseEntity<List<Transaccion>> obtenerTransaccionesPorIdCliente(@PathVariable Long idCliente) {
        List<Transaccion> transaccions = clienteService.obtenerTransaccionesPorIdCliente(idCliente);
        return ResponseEntity.ok(transaccions);
    }

    @GetMapping("/detallesTransacciones")
    public ResponseEntity<List<DetalleFinalTransaccion>> obtenerTodosLosDetallesTransacciones() {
        List<DetalleFinalTransaccion> detalleFinalTransaccions = clienteService.obtenerTodosLosDetallesTransacciones();
        return ResponseEntity.ok(detalleFinalTransaccions);
    }

    @GetMapping("/detallesTransacciones/compra/{idCompra}")
    public ResponseEntity<List<DetalleFinalTransaccion>> obtenerDetallesTransaccionesPorIdCompra(@PathVariable Long idCompra) {
        List<DetalleFinalTransaccion> detalleFinalTransaccions = clienteService.obtenerDetallesTransaccionesPorIdCompra(idCompra);
        return ResponseEntity.ok(detalleFinalTransaccions);
    }
    @GetMapping("/detallesTransacciones/cliente/{idCliente}")
    public ResponseEntity<List<DetalleFinalTransaccion>> obtenerDetallesTransaccionesPorIdCliente(@PathVariable Long idCliente) {
        List<DetalleFinalTransaccion> detallesTransaccionesPorIdCliente = clienteService.obtenerDetallesTransaccionesPorIdCliente(idCliente);
        return ResponseEntity.ok(detallesTransaccionesPorIdCliente);
    }

    @GetMapping("/getAll/{idCliente}")
    public ResponseEntity<Map<String, Object>> getAllInfo(@PathVariable int idCliente){
        Map<String,Object> result = clienteService.getUserAndAllTransaccions(idCliente);
        return ResponseEntity.ok(result);

    }
}
