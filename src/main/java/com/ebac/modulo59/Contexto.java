package com.ebac.modulo59;

import com.ebac.modulo59.models.DireccionModel;
import com.ebac.modulo59.models.UsuarioModel;
import com.ebac.modulo59.models.TelefonoModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Contexto {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // Registrar driver H2
        Class.forName("org.h2.Driver");

        String url = "jdbc:h2:./data/modulo59;MODE=MySQL;AUTO_SERVER=TRUE";
        String user = "sa";
        String password = "";

        MysqlConnection mysqlConnection = new MysqlConnection();
        Connection connection = mysqlConnection.getConnection(url, user, password);

        crearTablas(connection);
        insertarDatosIniciales(connection);

        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM usuarios");
        while(rs.next()){
            System.out.println(rs.getInt("idUsuario") + " " +
                    rs.getString("nombre") + " " +
                    rs.getInt("edad"));
        }

        // Ejemplo de CRUD con direcciones
        DireccionModel direccion = new DireccionModel(null, 1, "Calle Nueva", 100, "CDMX");
        guardarDireccion(connection, direccion);

        direccion.setEstado("Puebla");
        actualizarDireccion(connection, direccion);

        List<DireccionModel> direcciones = obtenerDirecciones(connection);
        direcciones.forEach(System.out::println);

        eliminarDireccion(connection, direccion.getIdDireccion());
    }

    private static void crearTablas(Connection conn) throws SQLException {
        Statement st = conn.createStatement();

        st.execute("CREATE TABLE IF NOT EXISTS usuarios (" +
                "idUsuario INT AUTO_INCREMENT PRIMARY KEY," +
                "nombre VARCHAR(100)," +
                "edad INT)");

        st.execute("CREATE TABLE IF NOT EXISTS direcciones (" +
                "idDireccion INT AUTO_INCREMENT PRIMARY KEY," +
                "idUsuario INT," +
                "calle VARCHAR(100)," +
                "numero INT," +
                "estado VARCHAR(100))");

        st.execute("CREATE TABLE IF NOT EXISTS telefonos (" +
                "idTelefono INT AUTO_INCREMENT PRIMARY KEY," +
                "idUsuario INT," +
                "numero VARCHAR(100)," +
                "tipo VARCHAR(100))");
    }

    private static void insertarDatosIniciales(Connection conn) throws SQLException {
        Statement st = conn.createStatement();
        st.execute("INSERT INTO usuarios(nombre, edad) VALUES ('Enrique',35), ('Laura',25), ('Josefina',40), ('Uriel',28)");
        st.execute("INSERT INTO telefonos(idUsuario, numero, tipo) VALUES " +
                "(1,'+52 654-2121-3232','celular')," +
                "(1,'+52 654-9874-1549','oficina')," +
                "(2,'+52 748-4982-0654','celular')," +
                "(3,'+52 963-0201-0036','celular')," +
                "(4,'+52 508-0014-0809','casa')");
        st.execute("INSERT INTO direcciones(idUsuario, calle, numero, estado) VALUES " +
                "(1,'Emiliano Zapata',24,'Jalisco')," +
                "(2,'Francisco Villa',101,'Zacatecas')," +
                "(2,'Avenida De los Insurgentes',5002,'Zacatecas')," +
                "(3,'Cerrada de los pinos',5,'Queretaro')," +
                "(4,'Privada rosales',900,'Guanajuato')," +
                "(4,'Avenida centra',246,'Guanajuato')");
    }

    public static void guardarDireccion(Connection conn, DireccionModel d) throws SQLException {
        String sql = "INSERT INTO direcciones(idUsuario, calle, numero, estado) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, d.getIdUsuario());
        ps.setString(2, d.getCalle());
        ps.setInt(3, d.getNumero());
        ps.setString(4, d.getEstado());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) d.setIdDireccion(rs.getInt(1));
    }

    public static void actualizarDireccion(Connection conn, DireccionModel d) throws SQLException {
        String sql = "UPDATE direcciones SET calle=?, numero=?, estado=? WHERE idDireccion=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, d.getCalle());
        ps.setInt(2, d.getNumero());
        ps.setString(3, d.getEstado());
        ps.setInt(4, d.getIdDireccion());
        ps.executeUpdate();
    }

    public static void eliminarDireccion(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM direcciones WHERE idDireccion=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public static List<DireccionModel> obtenerDirecciones(Connection conn) throws SQLException {
        List<DireccionModel> lista = new ArrayList<>();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM direcciones");
        while (rs.next()) {
            lista.add(new DireccionModel(
                    rs.getInt("idDireccion"),
                    rs.getInt("idUsuario"),
                    rs.getString("calle"),
                    rs.getInt("numero"),
                    rs.getString("estado")
            ));
        }
        return lista;
    }
}
