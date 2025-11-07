package Datos;

import BD.Conexion;
import Dominio.Proveedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO implements IProveedorDAO {

    @Override
    public List<Proveedor> listarProveedores() {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM Proveedor";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setId(rs.getString("idProveedor"));
                proveedor.setRazonSocial(rs.getString("razonSocial"));
                proveedor.setDni(rs.getString("dni"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedores.add(proveedor);
            }

        } catch (Exception e) {
            System.out.println("Error al listar proveedores: " + e.getMessage());
        }

        return proveedores;
    }

    @Override
    public boolean buscarProveedorPorId(Proveedor proveedor) {
        String sql = "SELECT * FROM Proveedor WHERE idProveedor = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, proveedor.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    proveedor.setRazonSocial(rs.getString("razonSocial"));
                    proveedor.setDni(rs.getString("dni"));
                    proveedor.setTelefono(rs.getString("telefono"));
                    return true;
                }
            }

        } catch (Exception e) {
            System.out.println("Error al buscar proveedor por ID: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean agregarProveedor(Proveedor proveedor) {
        String sql = "INSERT INTO Proveedor(idProveedor, razonSocial, dni, telefono) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, proveedor.getId());
            ps.setString(2, proveedor.getRazonSocial());
            ps.setString(3, proveedor.getDni());
            ps.setString(4, proveedor.getTelefono());
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error al agregar proveedor: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean modificarProveedor(Proveedor proveedor) {
        String sql = "UPDATE Proveedor SET razonSocial = ?, dni = ?, telefono = ? WHERE idProveedor = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, proveedor.getRazonSocial());
            ps.setString(2, proveedor.getDni());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getId());
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error al modificar proveedor: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminarProveedor(Proveedor proveedor) {
        String sql = "DELETE FROM Proveedor WHERE idProveedor = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, proveedor.getId());
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error al eliminar proveedor: " + e.getMessage());
        }
        return false;
    }
}
