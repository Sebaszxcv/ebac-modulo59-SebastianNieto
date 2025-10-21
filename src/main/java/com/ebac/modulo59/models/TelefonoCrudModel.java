package com.ebac.modulo59.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelefonoCrudModel {
    private final Connection connection;

    public TelefonoCrudModel(Connection connection) {
        this.connection = connection;
    }

    public void crearTabla() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS telefonos (" +
                "idTelefono IDENTITY PRIMARY KEY, " +
                "idUsuario INT, " +
                "numero VARCHAR(50), " +
                "tipo VARCHAR(50))";
        connection.createStatement().execute(sql);
    }

    public void insertar(Integer idUsuario, String numero, String tipo) throws SQLException {
        String sql = "INSERT INTO telefonos (idUsuario, numero, tipo) VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        ps.setString(2, numero);
        ps.setString(3, tipo);
        ps.executeUpdate();
        ps.close();
    }

    public List<TelefonoModel> listar() throws SQLException {
        List<TelefonoModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM telefonos";
        ResultSet rs = connection.createStatement().executeQuery(sql);
        while (rs.next()) {
            lista.add(new TelefonoModel(
                    rs.getInt("idTelefono"),
                    rs.getInt("idUsuario"),
                    rs.getString("numero"),
                    rs.getString("tipo")
            ));
        }
        rs.close();
        return lista;
    }

    public void actualizar(Integer idTelefono, String nuevoNumero, String nuevoTipo) throws SQLException {
        String sql = "UPDATE telefonos SET numero = ?, tipo = ? WHERE idTelefono = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, nuevoNumero);
        ps.setString(2, nuevoTipo);
        ps.setInt(3, idTelefono);
        ps.executeUpdate();
        ps.close();
    }

    public void eliminar(Integer idTelefono) throws SQLException {
        String sql = "DELETE FROM telefonos WHERE idTelefono = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, idTelefono);
        ps.executeUpdate();
        ps.close();
    }
}
