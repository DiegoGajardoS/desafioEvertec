package com.libreria.transaccionmicroservice.repository;

import com.libreria.transaccionmicroservice.entity.DetalleFInalTransaccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleFinalTransaccionRepository extends JpaRepository<DetalleFInalTransaccion, Long> {

    List<DetalleFInalTransaccion> findByIdCompra(Long idCompra);
    List<DetalleFInalTransaccion> findByIdCliente(Long idCompra);



}
