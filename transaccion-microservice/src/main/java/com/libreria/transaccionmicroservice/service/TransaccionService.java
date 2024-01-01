package com.libreria.transaccionmicroservice.service;

import com.libreria.transaccionmicroservice.entity.Transaccion;
import com.libreria.transaccionmicroservice.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaccionService {

    @Autowired
    TransaccionRepository transaccionRepository;

    public List<Transaccion> getAll(){
        return transaccionRepository.findAll();
    }
    public Transaccion getTransactionById(int id){
        return transaccionRepository.findById(id).orElse(null);
    }
    public Transaccion save(Transaccion transaccion){
        Transaccion transaccionNuevo = transaccionRepository.save(transaccion);
        return transaccionNuevo;
    }
}
