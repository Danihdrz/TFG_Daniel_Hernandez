
-- =========================================
-- 1. USUARIOS
-- =========================================
INSERT INTO usuarios (nombre, email, password, rol) VALUES
                                                        ('Juan Pérez', 'juan@socio.com', '1234', 'SOCIO'),        -- id_usuario = 1
                                                        ('Carlos Martín', 'carlos@socio.com', '1234', 'SOCIO'),   -- id_usuario = 2
                                                        ('Ana López', 'ana@socio.com', '1234', 'SOCIO'),          -- id_usuario = 3
                                                        ('Laura Gómez', 'laura@entrenador.com', '1234', 'ENTRENADOR'), -- id_usuario = 4
                                                        ('Admin', 'admin@gimnasio.com', 'admin123', 'ADMIN');     -- id_usuario = 5

-- =========================================
-- 2. SOCIOS
-- =========================================
INSERT INTO socios (id_usuario, direccion, telefono, fecha_nacimiento) VALUES
                                                                           (1, 'Calle Mayor 1', '600111222', '1995-06-15'),
                                                                           (2, 'Calle Sol 2', '600333444', '1990-03-10'),
                                                                           (3, 'Calle Luna 3', '600555666', '2000-12-01');

-- =========================================
-- 3. ENTRENADORES
-- =========================================
INSERT INTO entrenadores (id_usuario, especialidad, telefono) VALUES
    (4, 'CrossFit', '611111111');

-- =========================================
-- 4. CUOTAS
-- =========================================
INSERT INTO cuotas (nombre, precio, duracion) VALUES
                                                  ('Mensual', 25.00, 'MENSUAL'),
                                                  ('Trimestral', 70.00, 'TRIMESTRAL'),
                                                  ('Anual', 250.00, 'ANUAL');

-- =========================================
-- 5. CLASES
-- =========================================
INSERT INTO clases (nombre, descripcion, fecha_hora, duracion, aforo_max, id_entrenador) VALUES
                                                                                             ('CrossFit', 'Alta intensidad', '2026-04-21 10:00:00', 60, 20, 1),
                                                                                             ('Yoga', 'Relajación y estiramientos', '2026-04-21 18:00:00', 60, 15, 1);

-- =========================================
-- 6. PAGOS
-- =========================================
INSERT INTO pagos (id_socio, id_cuota, fecha_pago, monto) VALUES
                                                              (1, 1, CURDATE(), 25.00),
                                                              (2, 2, CURDATE(), 70.00),
                                                              (3, 1, CURDATE(), 25.00);

-- =========================================
-- 7. INSCRIPCIONES A CLASES
-- =========================================
INSERT INTO inscripciones_clases (id_socio, id_clase) VALUES
                                                          (1, 1),
                                                          (1, 2),
                                                          (2, 1),
                                                          (3, 2);

-- =========================================
-- 8. ASISTENCIAS
-- =========================================
INSERT INTO asistencias (id_socio, id_clase, fecha, tipo) VALUES
                                                              (1, 1, NOW(), 'CLASE'),
                                                              (1, 2, NOW(), 'CLASE'),
                                                              (2, 1, NOW(), 'CLASE'),
                                                              (3, 2, NOW(), 'CLASE');