package Datos;

import BD.Conexion;
import Dominio.Venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VentaDAO implements IVentaDAO {

    @Override
    public boolean registrarVenta(Venta venta) {
        String sql = "INSERT INTO Venta (idEmpleado, idCliente, total) VALUES (?, ?, ? )";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, venta.getIdEmpleado());
            ps.setInt(2, venta.getIdCliente());
            ps.setFloat(3, venta.getTotal());


            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error al registrar venta: " + e.getMessage());
        }
        return false;
    }


    public int obtenerUltimoIdVenta() {
        int id = 0;
        String sql = "SELECT MAX(idVenta) AS idVenta FROM venta";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                id = rs.getInt("idVenta");
            }

        } catch (Exception e) {
            System.out.println("Error al obtener último ID de venta: " + e.getMessage());
        }

        return id;
    }

}
