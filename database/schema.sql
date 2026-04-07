-- Base de datos
CREATE DATABASE IF NOT EXISTS gimnasio;
USE gimnasio;

-- Tabla usuarios
CREATE TABLE usuarios (
                          id_usuario INT AUTO_INCREMENT PRIMARY KEY,
                          nombre VARCHAR(50) NOT NULL,
                          email VARCHAR(100) UNIQUE NOT NULL,
                          password VARCHAR(255) NOT NULL,
                          rol ENUM('ADMIN','ENTRENADOR','SOCIO') NOT NULL,
                          fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla socios
CREATE TABLE socios (
                        id_socio INT AUTO_INCREMENT PRIMARY KEY,
                        id_usuario INT NOT NULL,
                        direccion VARCHAR(150),
                        telefono VARCHAR(20),
                        fecha_nacimiento DATE,
                        FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE
);

-- Tabla entrenadores
CREATE TABLE entrenadores (
                              id_entrenador INT AUTO_INCREMENT PRIMARY KEY,
                              id_usuario INT NOT NULL,
                              especialidad VARCHAR(50),
                              telefono VARCHAR(20),
                              FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE
);

-- Tabla clases
CREATE TABLE clases (
                        id_clase INT AUTO_INCREMENT PRIMARY KEY,
                        nombre VARCHAR(50) NOT NULL,
                        descripcion VARCHAR(200),
                        fecha_hora DATETIME NOT NULL,
                        duracion INT NOT NULL, -- en minutos
                        aforo_max INT NOT NULL,
                        id_entrenador INT NOT NULL,
                        FOREIGN KEY (id_entrenador) REFERENCES entrenadores(id_entrenador) ON DELETE CASCADE
);

-- Tabla cuotas
CREATE TABLE cuotas (
                        id_cuota INT AUTO_INCREMENT PRIMARY KEY,
                        nombre VARCHAR(50) NOT NULL,
                        precio DECIMAL(7,2) NOT NULL,
                        duracion ENUM('MENSUAL','TRIMESTRAL','ANUAL') NOT NULL
);

-- Tabla pagos
CREATE TABLE pagos (
                       id_pago INT AUTO_INCREMENT PRIMARY KEY,
                       id_socio INT NOT NULL,
                       id_cuota INT NOT NULL,
                       fecha_pago DATE NOT NULL,
                       monto DECIMAL(7,2) NOT NULL,
                       FOREIGN KEY (id_socio) REFERENCES socios(id_socio) ON DELETE CASCADE,
                       FOREIGN KEY (id_cuota) REFERENCES cuotas(id_cuota)
);

-- Tabla asistencias
CREATE TABLE asistencias (
                             id_asistencia INT AUTO_INCREMENT PRIMARY KEY,
                             id_socio INT NOT NULL,
                             id_clase INT,
                             fecha DATETIME NOT NULL,
                             tipo ENUM('GIMNASIO','CLASE') NOT NULL,
                             FOREIGN KEY (id_socio) REFERENCES socios(id_socio) ON DELETE CASCADE,
                             FOREIGN KEY (id_clase) REFERENCES clases(id_clase) ON DELETE CASCADE
);

-- Tabla inscripciones_clases
CREATE TABLE inscripciones_clases (
                                      id_inscripcion INT AUTO_INCREMENT PRIMARY KEY,
                                      id_socio INT NOT NULL,
                                      id_clase INT NOT NULL,
                                      fecha_inscripcion DATETIME DEFAULT CURRENT_TIMESTAMP,
                                      FOREIGN KEY (id_socio) REFERENCES socios(id_socio) ON DELETE CASCADE,
                                      FOREIGN KEY (id_clase) REFERENCES clases(id_clase) ON DELETE CASCADE,
                                      UNIQUE KEY unico_socio_clase (id_socio, id_clase)
);