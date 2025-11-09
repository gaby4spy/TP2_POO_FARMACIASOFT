package Datos;

import BD.Conexion;
import Dominio.Ticket;
import Dominio.Venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class TicketDAO implements  ITicketDAO{
    @Override
    public List<Ticket> generarTicket(Venta venta) {
        List<Ticket> ticketDetalles = new ArrayList<>();
        String sql = """
            SELECT v.total, dv.cantidad, m.nombre AS nombreMedicamento,
                   m.precio, c.nombre AS nombreCliente, c.dni
            FROM venta v
            INNER JOIN detalleventa dv ON v.idVenta = dv.idVenta
            INNER JOIN lotemedicamento l ON dv.idLote = l.idCompra
            INNER JOIN medicamento m ON m.idMedicamento = l.idMedicamento
            INNER JOIN cliente c ON c.idCliente = v.idCliente
            WHERE v.idVenta = ?
        """;

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, venta.getIdVenta());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Ticket detalle = new Ticket();
                    detalle.setTotal(rs.getFloat("total"));
                    detalle.setNombreMedicamento(rs.getString("nombreMedicamento"));
                    detalle.setPrecio(rs.getFloat("precio"));
                    detalle.setNombreCliente(rs.getString("nombreCliente"));
                    detalle.setDni(rs.getInt("dni"));
                    detalle.setCantidad(rs.getInt("cantidad"));

                    ticketDetalles.add(detalle);
                }
            }

        } catch (Exception e) {
            System.out.println("Error al generar ticket: " + e.getMessage());
        }

        return ticketDetalles;
    }
}
