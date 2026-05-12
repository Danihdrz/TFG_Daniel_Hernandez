# Gimnasio

Backend Spring Boot para la gestion de usuarios, clases, cuotas y pagos de un gimnasio.

## Requisitos

- Java 17
- Maven Wrapper incluido
- MySQL 8

## Configuracion

Copia `.env.example` a `.env` o define estas variables de entorno:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`
- `DDL_AUTO`
- `JWT_SECRET`
- `FRONTEND_ORIGIN`

Por defecto `DDL_AUTO` usa `validate`.

## Ejecutar

```powershell
.\mvnw.cmd spring-boot:run
```

## Tests

```powershell
.\mvnw.cmd test
```
