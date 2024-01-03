package com.libreria.clientemicroservice.service;


import com.libreria.clientemicroservice.entity.Cliente;
import com.libreria.clientemicroservice.feignClients.TransaccionFeignClient;
import com.libreria.clientemicroservice.model.CompraRequestDTO;
import com.libreria.clientemicroservice.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    TransaccionFeignClient transaccionFeignClient;

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    public Cliente getClienteById(int id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente save(Cliente cliente) {
        Cliente clienteNuevo = clienteRepository.save(cliente);
        return clienteNuevo;
    }
    public ResponseEntity<String> realizarTransaccionDesdeCliente(Long idCliente, CompraRequestDTO compraRequestDTO) {
        compraRequestDTO.setIdCliente(idCliente);
        return transaccionFeignClient.realizarCompra(compraRequestDTO);
    }

}

