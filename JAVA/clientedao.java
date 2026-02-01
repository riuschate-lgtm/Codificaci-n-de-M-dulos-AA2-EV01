package com.elektrasas.dao;

import com.elektrasas.model.Cliente;
import com.elektrasas.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void insertarCliente(Cliente cliente) {
        String sql = "INSERT INTO elusuar_cliente " +
                "(Primer_Nombre, Apellido_Paterno, email, password, Estado) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getPrimerNombre());
            ps.setString(2, cliente.getApellidoPaterno());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getPassword());
            ps.setString(5, cliente.getEstado());

            ps.executeUpdate();
            System.out.println("Cliente insertado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al insertar cliente: " + e.getMessage());
        }
    }

    public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM elusuar_cliente";

        try (Connection conn = ConexionBD.obtenerConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("ID_Cliente"));
                cliente.setPrimerNombre(rs.getString("Primer_Nombre"));
                cliente.setApellidoPaterno(rs.getString("Apellido_Paterno"));
                cliente.setEmail(rs.getString("email"));
                cliente.setEstado(rs.getString("Estado"));
                lista.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }
        return lista;
    }

    public void actualizarEstadoCliente(int idCliente, String estado) {
        String sql = "UPDATE elusuar_cliente SET Estado=? WHERE ID_Cliente=?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, estado);
            ps.setInt(2, idCliente);
            ps.executeUpdate();
            System.out.println("Estado actualizado");

        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        }
    }

    public void eliminarCliente(int idCliente) {
        String sql = "DELETE FROM elusuar_cliente WHERE ID_Cliente=?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ps.executeUpdate();
            System.out.println("Cliente eliminado");

        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        }
    }
}
