# Booksy Backend

Backend de la aplicacion Booksy para la gestion de libros.

## Tecnologias usadas

- Spring Boot 3.2.0
- MongoDB
- Java 17

## Endpoints

- `GET /api/libros` - obtener todos los libros
- `GET /api/libros/{id}` - obtener un libro por id
- `GET /api/libros/buscar?titulo=texto` - buscar libros por titulo
- `POST /api/libros` - crear un libro nuevo
- `PUT /api/libros/{id}` - actualizar un libro
- `DELETE /api/libros/{id}` - eliminar un libro

## Variables de entorno

- `PORT` - puerto del servidor (default: 8080)
- `MONGODB_URI` - uri de conexion a mongodb

## Como ejecutar

```bash
mvn spring-boot:run
```
