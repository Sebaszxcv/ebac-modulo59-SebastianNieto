package com.ebac.modulo59.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioCrudModel {
    private final Connection connection;

    public UsuarioCrudModel(Connection connection) {
        this.connection = connection;
    }

    public void crearTabla() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
                "idUsuario IDENTITY PRIMARY KEY, " +
                "nombre VARCHAR(100), " +
                "edad INT)";
        connection.createStatement().execute(sql);
    }

    public void insertar(String nombre, Integer edad) throws SQLException {
        String sql = "INSERT INTO usuarios (nombre, edad) VALUES (?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, nombre);
        ps.setInt(2, edad);
        ps.executeUpdate();
        ps.close();
    }

    public List<UsuarioModel> listar() throws SQLException {
        List<UsuarioModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        ResultSet rs = connection.createStatement().executeQuery(sql);
        while (rs.next()) {
            lista.add(new UsuarioModel(
                    rs.getInt("idUsuario"),
                    rs.getString("nombre"),
                    rs.getInt("edad")
            ));
        }
        rs.close();
        return lista;
    }

    public void actualizar(Integer idUsuario, String nuevoNombre, Integer nuevaEdad) throws SQLException {
        String sql = "UPDATE usuarios SET nombre = ?, edad = ? WHERE idUsuario = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, nuevoNombre);
        ps.setInt(2, nuevaEdad);
        ps.setInt(3, idUsuario);
        ps.executeUpdate();
        ps.close();
    }

    public void eliminar(Integer idUsuario) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE idUsuario = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        ps.executeUpdate();
        ps.close();
    }
}
