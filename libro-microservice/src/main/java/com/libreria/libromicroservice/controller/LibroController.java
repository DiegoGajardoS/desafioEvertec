package com.libreria.libromicroservice.controller;

import com.libreria.libromicroservice.entity.Libro;
import com.libreria.libromicroservice.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    LibroService libroService;

    @GetMapping
    public ResponseEntity<List<Libro>> getaAll() {

        List<Libro> libros = libroService.getAll();
        if(libros.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> getById(@PathVariable("id") int id) {

        Libro libro = libroService.getLibroById(id);
        if(libro == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(libro);
    }

    @PostMapping
    public ResponseEntity<Libro> save(@RequestBody Libro libro) {

        Libro libroNuevo = libroService.save(libro);
        return ResponseEntity.ok(libroNuevo);
    }

}
