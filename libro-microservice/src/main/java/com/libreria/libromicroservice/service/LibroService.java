package com.libreria.libromicroservice.service;
import com.libreria.libromicroservice.entity.Libro;
import com.libreria.libromicroservice.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public Optional<Libro> actualizarStock(int idLibro, int nuevoStock) {
        Optional<Libro> optionalLibro = libroRepository.findById(idLibro);

        if (optionalLibro.isPresent()) {
            Libro libro = optionalLibro.get();
            libro.setStock(nuevoStock);
            return Optional.of(libroRepository.save(libro));
        } else {
            // Manejo si el libro no se encuentra por el ID
            throw new NoSuchElementException("Libro no encontrado con ID: " + idLibro);
        }
    }
}
