package com.libreria.clientemicroservice.service;


import com.libreria.clientemicroservice.entity.Cliente;
import com.libreria.clientemicroservice.feignClients.LibroFeignClient;
import com.libreria.clientemicroservice.feignClients.TransaccionFeignClient;
import com.libreria.clientemicroservice.model.*;
import com.libreria.clientemicroservice.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.management.ObjectName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
        if (StringUtils.hasText(cliente.getNombre()) && StringUtils.hasText(cliente.getCorreo())) {
            if (isValidEmail(cliente.getCorreo())) {
                Cliente clienteNuevo = clienteRepository.save(cliente);
                return clienteNuevo;
            } else {
                throw new IllegalArgumentException("El formato del correo electrónico no es válido.");
            }
        } else {
            throw new IllegalArgumentException("Nombre y correo son campos requeridos para un cliente.");
        }
    }

    // Método para validar el formato del correo electrónico con una expresión regular
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
    public ResponseEntity<String> realizarTransaccionDesdeCliente(Long idCliente, CompraRequestDTO compraRequestDTO) {
        List<DetalleCompraDTO> detallesCompraDTOS = compraRequestDTO.getDetallesCompraDTOS();
        if (detallesCompraDTOS == null){
            return ResponseEntity.notFound().build();
        }
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
        List<Transaccion> transaccions = transaccionFeignClient.obtenerPorIdCliente(idCliente);
        if(transaccions.isEmpty()){
            System.out.println("No hay transacciones para este cliente");
        }
        return transaccions;
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
    public Map<String, Object> getUserAndAllTransaccions(int idCliente){
        Map<String,Object> result = new HashMap<>();
        Cliente cliente = clienteRepository.findById(idCliente).orElse(null);
        if(cliente == null){
            result.put("Mensaje", "no existe el cliente");
            return result;
        }
        result.put("Cliente", cliente);
        Long longIdCliente = Long.valueOf(idCliente);
        List<Transaccion> transaccions = transaccionFeignClient.obtenerPorIdCliente(longIdCliente);
        List<DetalleFinalTransaccion> detalleFinalTransaccions = transaccionFeignClient.obtenerDetallePorIdCliente(longIdCliente);
        if(transaccions.isEmpty()){
            result.put("Transacciones","El cliente no ha realizado compras");
            result.put("Detalles compras","No ha realizado compras, por lo tanto no hay detalles de compra");
        }
        else {
            List<Map<String, Object>> compras = new ArrayList<>();

            for (DetalleFinalTransaccion detalleFinalTransaccion : detalleFinalTransaccions) {
                Map<String, Object> compraInfo = new HashMap<>();
                int librosComprados = 0;
                Long idCompra = detalleFinalTransaccion.getIdCompra();
                List<Transaccion> transaccionesCompras = transaccionFeignClient.obtenerPorIdCompra(idCompra);
                List<Map<String, Object>> librosCompradosEnCompra = new ArrayList<>();

                for (Transaccion transaccionCompra : transaccionesCompras) {
                    int librosCompradosEnTransaccion = transaccionCompra.getCantidadLibro();
                    librosComprados += librosCompradosEnTransaccion;

                    Map<String, Object> libroInfo = new HashMap<>();
                    Long idLibro = transaccionCompra.getIdlibro();
                    int intIdLibro = idLibro.intValue();
                    Libro libroEnCompra = obtenerLibro(intIdLibro);

                    libroInfo.put("Libro", libroEnCompra);
                    librosCompradosEnCompra.add(libroInfo); // Agregar detalles de libro a la lista
                }

                compraInfo.put("id Compra", idCompra);
                compraInfo.put("Precio final de compra", detalleFinalTransaccion.getPrecioCompra());
                compraInfo.put("Cantidad de libros comprados", librosComprados);
                compraInfo.put("Fecha de compra", detalleFinalTransaccion.getFechaCompra());
                compraInfo.put("Libros comprados", librosCompradosEnCompra); // Asignar lista de libros al mapa

                compras.add(compraInfo);
            }

            result.put("Compras", compras);
            result.put("Transacciones", transaccions);
        }
        return result;
    }
}

