package com.libreria.libromicroservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private int stock;
    private String autor;
    private String genero;
    @Column(columnDefinition = "INT")
    private int precio;
    private String editorial;

    public void setStock(int stock) {
        this.stock = stock;
    }
}
