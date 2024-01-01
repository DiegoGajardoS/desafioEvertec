package com.libreria.clientemicroservice.repository;

import com.libreria.clientemicroservice.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
