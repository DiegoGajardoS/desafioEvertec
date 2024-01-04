package com.libreria.transaccionmicroservice.repository;

import com.libreria.transaccionmicroservice.entity.DetalleFInalTransaccion;
import com.libreria.transaccionmicroservice.entity.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {

    List<Transaccion> findByIdCliente(Long idCliente);
    List<Transaccion> findByIdCompra(Long idCompra);

}
