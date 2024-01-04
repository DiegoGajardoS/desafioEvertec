package com.libreria.transaccionmicroservice.repository;

import com.libreria.transaccionmicroservice.entity.DetalleFInalTransaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleFinalTransaccionRepository extends JpaRepository<DetalleFInalTransaccion, Integer> {
}
