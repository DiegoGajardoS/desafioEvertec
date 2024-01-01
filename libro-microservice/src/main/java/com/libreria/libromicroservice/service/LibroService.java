package com.libreria.libromicroservice.service;
import com.libreria.libromicroservice.entity.Libro;
import com.libreria.libromicroservice.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class LibroService {
    @Autowired
    LibroRepository libroRepository;

    public List<Libro> getAll(){
        return libroRepository.findAll();
    }

    public Libro getLibroById(int id){
        return libroRepository.findById(id).orElse(null);
    }

    public Libro save(Libro libro){
        Libro libroNuevo = libroRepository.save(libro);
        return libroNuevo;
    }
}
