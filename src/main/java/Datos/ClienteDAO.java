package Datos;

import BD.Conexion;
import Dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO {

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("idCliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDni(rs.getString("dni"));
                cliente.setTelefono(rs.getString("telefono"));
                clientes.add(cliente);
            }

        } catch (Exception e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }

        return clientes;
    }

    @Override
    public boolean buscarClientePorId(Cliente cliente) {
        String sql = "SELECT * FROM Cliente WHERE idCliente = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cliente.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setDni(rs.getString("dni"));
                    cliente.setTelefono(rs.getString("telefono"));
                    return true;
                }
            }

        } catch (Exception e) {
            System.out.println("Error al buscar cliente por ID: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente(idCliente, nombre, dni, telefono) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cliente.getId());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getDni());
            ps.setString(4, cliente.getTelefono());
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        String sql = "UPDATE Cliente SET nombre = ?, dni = ?, telefono = ? WHERE idCliente = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDni());
            ps.setString(3, cliente.getTelefono());
            ps.setInt(4, cliente.getId());
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error al modificar cliente: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        String sql = "DELETE FROM Cliente WHERE idCliente = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cliente.getId());
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        }
        return false;
    }
}
