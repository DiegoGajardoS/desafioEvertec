package com.libreria.clientemicroservice.service;


import com.libreria.clientemicroservice.entity.Cliente;
import com.libreria.clientemicroservice.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> getAll(){
        return clienteRepository.findAll();
    }
    public Cliente getClienteById(int id){
        return clienteRepository.findById(id).orElse(null);
    }
    public Cliente save(Cliente cliente){
        Cliente clienteNuevo = clienteRepository.save(cliente);
        return clienteNuevo;
    }
}

