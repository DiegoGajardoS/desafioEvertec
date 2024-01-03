package com.libreria.clientemicroservice.feignClients;

import com.libreria.clientemicroservice.model.CompraRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "transaccion-microservice", url = "http://localhost:8003")
public interface TransaccionFeignClient {

    @PostMapping("transaccion/nuevaTransaccion")
    ResponseEntity<String> realizarCompra(@RequestBody CompraRequestDTO compraRequestDTO);
}
