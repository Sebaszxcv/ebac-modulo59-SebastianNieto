package com.ebac.modulo59;

import com.ebac.modulo59.models.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Contexto {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/modulo59db")) {

            // ---------- LIMPIAR TABLAS PARA EVITAR DUPLICADOS ----------
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("DROP TABLE IF EXISTS telefonos");
                stmt.execute("DROP TABLE IF EXISTS direcciones");
                stmt.execute("DROP TABLE IF EXISTS usuarios");
            }

            // ---------- USUARIOS ----------
            UsuarioCrudModel usuarioCrud = new UsuarioCrudModel(connection);
            usuarioCrud.crearTabla();
            usuarioCrud.insertar("Sebastián Nieto", 28);
            usuarioCrud.insertar("Laura García", 32);
            System.out.println("Usuarios:");
            usuarioCrud.listar().forEach(System.out::println);

            // ---------- TELÉFONOS ----------
            TelefonoCrudModel telefonoCrud = new TelefonoCrudModel(connection);
            telefonoCrud.crearTabla();
            telefonoCrud.insertar(1, "555-1234", "Móvil");
            telefonoCrud.insertar(2, "555-6789", "Casa");
            System.out.println("\nTeléfonos:");
            telefonoCrud.listar().forEach(System.out::println);

            // ---------- DIRECCIONES ----------
            DireccionCrudModel direccionCrud = new DireccionCrudModel(connection);
            direccionCrud.crearTabla();
            direccionCrud.insertar(new DireccionModel(null, "Madrid", "España"));
            direccionCrud.insertar(new DireccionModel(null, "Ciudad de México", "México"));
            System.out.println("\nDirecciones:");
            direccionCrud.listar().forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
