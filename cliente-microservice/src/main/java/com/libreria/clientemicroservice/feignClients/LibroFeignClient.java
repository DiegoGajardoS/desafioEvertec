package com.libreria.clientemicroservice.feignClients;
import com.libreria.clientemicroservice.model.Libro;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "libro-microservice", url = "http://localhost:8001")

public interface LibroFeignClient {

    @GetMapping("libro/{id}")
    Libro getLibroById(@PathVariable("id") int id);

    @PutMapping("/libro/actualizarStock/{idLibro}")
    void actualizarStock(@PathVariable("idLibro") int idLibro, @RequestBody int nuevoStock);
}
