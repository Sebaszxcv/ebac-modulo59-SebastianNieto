package com.ebac.modulo59.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DireccionCrudModel {

    private Connection connection;

    public DireccionCrudModel(Connection connection) {
        this.connection = connection;
    }

    public void crearTabla() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS direcciones");
            stmt.execute("""
                CREATE TABLE direcciones (
                    id IDENTITY PRIMARY KEY,
                    ciudad VARCHAR(100),
                    pais VARCHAR(100)
                )
                """);
        }
    }

    public void insertar(DireccionModel direccion) throws SQLException {
        String sql = "INSERT INTO direcciones (ciudad, pais) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, direccion.getCiudad());
            ps.setString(2, direccion.getPais());
            ps.executeUpdate();
        }
    }

    public List<DireccionModel> listar() throws SQLException {
        List<DireccionModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM direcciones";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                DireccionModel d = new DireccionModel(
                        rs.getInt("id"),
                        rs.getString("ciudad"),
                        rs.getString("pais")
                );
                lista.add(d);
            }
        }
        return lista;
    }

    public void actualizar(int id, String nuevaCiudad, String nuevoPais) throws SQLException {
        String sql = "UPDATE direcciones SET ciudad=?, pais=? WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nuevaCiudad);
            ps.setString(2, nuevoPais);
            ps.setInt(3, id);
            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM direcciones WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
