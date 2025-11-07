package Datos;
import BD.Conexion;
import Dominio.DetalleVenta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
public class DetalleVentaDAO implements IDetalleVentaDAO {

    @Override
    public boolean agregarDetalleVenta(DetalleVenta detalle) {
        String sql = "INSERT INTO DetalleVenta (idVenta, idLote, cantidad, precioUnitario, total, fecha) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, detalle.getIdVenta());
            ps.setInt(2, detalle.getIdLote());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getPrecioUnitario());
            ps.setDouble(5, detalle.getTotal());
            ps.setTimestamp(6, Timestamp.valueOf(detalle.getFecha()));

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error al agregar detalle de venta: " + e.getMessage());
        }
        return false;
    }
}
