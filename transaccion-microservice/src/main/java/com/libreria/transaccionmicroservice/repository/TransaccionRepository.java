package com.libreria.transaccionmicroservice.repository;

import com.libreria.transaccionmicroservice.entity.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {
}
