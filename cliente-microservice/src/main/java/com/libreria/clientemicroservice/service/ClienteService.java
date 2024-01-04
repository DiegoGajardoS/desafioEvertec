package com.libreria.clientemicroservice.service;


import com.libreria.clientemicroservice.entity.Cliente;
import com.libreria.clientemicroservice.feignClients.LibroFeignClient;
import com.libreria.clientemicroservice.feignClients.TransaccionFeignClient;
import com.libreria.clientemicroservice.model.*;
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

    @Autowired
    LibroFeignClient libroFeignClient;

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
        //hacer un llamado a libro para traer los datos del libro y ver si puedo comprar segun stock
        List<DetalleCompraDTO> detallesCompraDTOS = compraRequestDTO.getDetallesCompraDTOS();
        int validador = 0;
        int cantidadCompras = detallesCompraDTOS.size();
        for(DetalleCompraDTO detalleCompraDTO : detallesCompraDTOS){
            //consulta stock
            Long idLibro = detalleCompraDTO.getIdLibro();
            int idLibroInt = idLibro.intValue();
            Libro libroActual = obtenerLibro(idLibroInt);
            int cantidadComprar = detalleCompraDTO.getCantidad();
            int stockLibroActual = libroActual.getStock();
            if( cantidadComprar > stockLibroActual){
                return ResponseEntity.notFound().build();
            }else{
                validador += 1;
            }
          }
        if(validador == cantidadCompras){
            int precioLibros = 0;
            for(DetalleCompraDTO detalleCompraDTO : detallesCompraDTOS){
                //guardar precio
                int precioLibro = 0;
                Long idLibro = detalleCompraDTO.getIdLibro();
                int idLibroInt = idLibro.intValue();
                Libro libroActual = obtenerLibro(idLibroInt);
                int cantidadComprar = detalleCompraDTO.getCantidad();
                int stockLibroActual = libroActual.getStock();
                precioLibro = libroActual.getPrecio() * cantidadComprar;
                precioLibros = precioLibros + precioLibro;
                int nuevoStock = stockLibroActual - cantidadComprar;
                actualizarStockDelLibro(idLibroInt,nuevoStock);
            }
            compraRequestDTO.setPrecioCompra(precioLibros);
            compraRequestDTO.setIdCliente(idCliente);
            return transaccionFeignClient.realizarCompra(compraRequestDTO);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    public Libro obtenerLibro(int id){
        return libroFeignClient.getLibroById(id);
    }
    public void actualizarStockDelLibro(int idLibro, int nuevoStock) {
        libroFeignClient.actualizarStock(idLibro, nuevoStock);
    }

    public List<Transaccion> obtenerTodasLasTransacciones() {
        return transaccionFeignClient.obtenerTodasLasTransacciones();
    }

    public List<Transaccion> obtenerTransaccionesPorIdCompra(Long idCompra) {
        return transaccionFeignClient.obtenerPorIdCompra(idCompra);
    }

    public List<Transaccion> obtenerTransaccionesPorIdCliente(Long idCliente) {
        return transaccionFeignClient.obtenerPorIdCliente(idCliente);
    }

    public List<DetalleFinalTransaccion> obtenerTodosLosDetallesTransacciones() {
        return transaccionFeignClient.obtenerTodosLosDetallesFinales();
    }

    public List<DetalleFinalTransaccion> obtenerDetallesTransaccionesPorIdCompra(Long idCompra) {
        return transaccionFeignClient.obtenerDetallePorIdCompra(idCompra);
    }

    public List<DetalleFinalTransaccion> obtenerDetallesTransaccionesPorIdCliente(Long idCliente) {
        return transaccionFeignClient.obtenerDetallePorIdCliente(idCliente);
    }
}

