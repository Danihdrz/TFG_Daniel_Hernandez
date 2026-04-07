package org.example.gimnasio.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class DatabaseTest implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void run(String... args) throws Exception {
        try {
            // Ejecutamos un query simple para comprobar la conexión
            entityManager.createNativeQuery("SELECT 1").getSingleResult();
            System.out.println("✅ Conexión a la base de datos MySQL exitosa.");
        } catch (Exception e) {
            System.err.println("❌ Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}