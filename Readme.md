# Desafío Evertec

Backend de librería con microservicios

## Autor

Diego Gajardo Salgado

## Entorno de desarrollo

- Java 17
- SpringBoot
- PostgreSQL 16
- Maven project

## Consideraciones

- la base de datos se debe configurar en los aplication.properties de cada microservicio
- Al ejecutar el programa se cargan las tablas del proyecto, no obstante no se cargan los libros en libreria
- Para la carga se tiene un script SQL en el repositorio llamado <'libros.SQL'> el cual debe ser ejecutado en el gestor de base de datos que estime conveniente
- El microservicio de cliente es el que consume el resto de microservicios, por lo tanto, desde el se realizan las consultas


## Servicios solicitados

- Crear modelo en cualquier motor de BD.
- Crear servicio REST POST para el ingreso de un cliente.
- Crear servicio REST POST para el ingreso de una transacción de compra.
- Crear servicio REST GET para obtener los datos de transacciones registradas.
- Crear servicio REST GET para obtener los datos de clientes registrados.


## Peticiones para consumir servicios en postman

### Ingresar cliente

- tipo de metodo: POST
- Url: localhost:8002/cliente
- JSON esperado: {
  "nombre": "Ejemplo 6",
  "correo": "ejemplo6@test.cl"
}

### Ingresar transaccion de compra

#### Transaccion de 2 o mas libros distintos con x cantidad cada uno
- Tipo de metodo: POST
- Url: localhost:8002/cliente/realizarCompra/{IdCliente}
- JSON esperado: {{
  "detallesCompraDTOS": [
    {"idLibro": 1, "cantidad": 5},
    {"idLibro": 4, "cantidad": 5}
  ]
}}
#### Transaccion de un solo libro con x cantidad

- Tipo de metodo: POST
- Url: localhost:8002/cliente/realizarCompra/{IdCliente}
- JSON esperado: {{
  "detallesCompraDTOS": [
    {"idLibro": 4, "cantidad": 5}
  ]
}}

### Obtener todas las transacciones registradas

- Tipo de metodo: GET
- Url: localhost:8002/cliente/transacciones

### Obtener datos de clientes registrados

- Tipo de metodo: GET
- Url: localhost:8002/cliente

### Obtener los datos de cuantos libros compro un cliente, el precio final de transaccion y el detalle de compra:

- Tipo de metodo: GET
- Url: localhost:8002/cliente/getAll/{idCliente}

**Este metodo obtiene los detalles por cliente, identificando cuantos libros compro en cada transaccion, que libros eran, el precio total de la compra, la fecha de la compra, las transacciones realizadas y los detalles del cliente**

