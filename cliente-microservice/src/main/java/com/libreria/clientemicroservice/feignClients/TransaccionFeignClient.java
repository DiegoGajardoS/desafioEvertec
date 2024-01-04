package com.libreria.clientemicroservice.feignClients;

import com.libreria.clientemicroservice.model.CompraRequestDTO;
import com.libreria.clientemicroservice.model.DetalleFinalTransaccion;
import com.libreria.clientemicroservice.model.Transaccion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "transaccion-microservice", url = "http://localhost:8003")
public interface TransaccionFeignClient {

    @PostMapping("transaccion/nuevaTransaccion")
    ResponseEntity<String> realizarCompra(@RequestBody CompraRequestDTO compraRequestDTO);

    @GetMapping("transaccion")
    List<Transaccion> obtenerTodasLasTransacciones();

    @GetMapping("transaccion/por-compra/{idCompra}")
    List<Transaccion> obtenerPorIdCompra(@PathVariable Long idCompra);

    @GetMapping("transaccion/por-cliente/{idCliente}")
    List<Transaccion> obtenerPorIdCliente(@PathVariable Long idCliente);

    @GetMapping("detalleTransaccion")
    List<DetalleFinalTransaccion> obtenerTodosLosDetallesFinales();

    @GetMapping("detalleTransaccion/por-compra/{idCompra}")
    List<DetalleFinalTransaccion> obtenerDetallePorIdCompra(@PathVariable Long idCompra);

    @GetMapping("detalleTransaccion/por-cliente/{idCliente}")
    List<DetalleFinalTransaccion> obtenerDetallePorIdCliente(@PathVariable Long idCliente);
}
