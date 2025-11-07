package Datos;

import Dominio.LoteMedicamento;
import Dominio.Medicamento;
import Dominio.Proveedor;
import BD.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class LoteMedicamentoDAO implements ILoteMedicamentoDAO {

    @Override
    public boolean agregarLote(LoteMedicamento lote) {
        String sql = """
            INSERT INTO LoteMedicamento 
            (fechaCompra, lote, fechaVencimiento, cantidadAdquirida, laboratorio, idMedicamento, idProveedor)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, java.sql.Date.valueOf(lote.getFechaCompra()));
            ps.setString(2, lote.getLote());
            ps.setDate(3, java.sql.Date.valueOf(lote.getFechaVencimiento()));
            ps.setInt(4, lote.getCantidadAdquirida());
            ps.setString(5, lote.getLaboratorio());
            ps.setInt(6, lote.getMedicamento().getIdMedicamento());
            ps.setInt(7, Integer.parseInt(lote.getProveedor().getId()));

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error al agregar lote: " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<LoteMedicamento> listarLotes() {
        List<LoteMedicamento> lotes = new ArrayList<>();
        String sql = "SELECT * FROM LoteMedicamento";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Medicamento med = new Medicamento(rs.getInt("idMedicamento"));
                Proveedor prov = new Proveedor(rs.getString("idProveedor"));

                LoteMedicamento lote = new LoteMedicamento(
                        rs.getInt("idCompra"),
                        rs.getDate("fechaCompra").toLocalDate(),
                        rs.getString("lote"),
                        rs.getDate("fechaVencimiento").toLocalDate(),
                        rs.getInt("cantidadAdquirida"),
                        rs.getString("laboratorio"),
                        med,
                        prov
                );

                lotes.add(lote);
            }

        } catch (Exception e) {
            System.out.println("Error al listar lotes: " + e.getMessage());
        }

        return lotes;
    }

    @Override
    public List<LoteMedicamento> buscarPorMedicamento(String nombreMedicamento) {
        List<LoteMedicamento> lotes = new ArrayList<>();
        String sql = """
            SELECT l.*
            FROM LoteMedicamento l
            INNER JOIN Medicamento m ON l.idMedicamento = m.idMedicamento
            WHERE m.nombre LIKE ?
        """;

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + nombreMedicamento + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Medicamento med = new Medicamento(rs.getInt("idMedicamento"));
                    Proveedor prov = new Proveedor(rs.getString("idProveedor"));

                    LoteMedicamento lote = new LoteMedicamento(
                            rs.getInt("idCompra"),
                            rs.getDate("fechaCompra").toLocalDate(),
                            rs.getString("lote"),
                            rs.getDate("fechaVencimiento").toLocalDate(),
                            rs.getInt("cantidadAdquirida"),
                            rs.getString("laboratorio"),
                            med,
                            prov
                    );

                    lotes.add(lote);
                }
            }

        } catch (Exception e) {
            System.out.println("Error al buscar lotes por medicamento: " + e.getMessage());
        }

        return lotes;
    }


    public List<LoteMedicamento> buscarPorProveedor(String idProveedor) {
        List<LoteMedicamento> lotes = new ArrayList<>();

        String sql = """
        SELECT 
            l.idCompra,
            l.lote,
            l.fechaCompra,
            l.fechaVencimiento,
            l.cantidadAdquirida,
            l.laboratorio,
            m.idMedicamento,
            m.nombre AS nombreMedicamento,
            p.idProveedor,
            p.razonSocial AS nombreProveedor
        FROM LoteMedicamento l
        INNER JOIN Medicamento m ON l.idMedicamento = m.idMedicamento
        INNER JOIN Proveedor p ON l.idProveedor = p.idProveedor
        WHERE p.idProveedor = ?
    """;

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(idProveedor) );

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Medicamento con constructor corto

                    Medicamento med = new Medicamento( rs.getInt("idMedicamento"),
                            rs.getString("nombreMedicamento"));

                    // Proveedor (tu clase usa String como ID)
                    Proveedor prov = new Proveedor(
                            String.valueOf(rs.getInt("idProveedor")),
                            rs.getString("nombreProveedor"),
                            null,
                            null
                    );

                    // Lote completo
                    LoteMedicamento lote = new LoteMedicamento(
                            rs.getInt("idCompra"),
                            rs.getDate("fechaCompra").toLocalDate(),
                            rs.getString("lote"),
                            rs.getDate("fechaVencimiento").toLocalDate(),
                            rs.getInt("cantidadAdquirida"),
                            rs.getString("laboratorio"),
                            med,
                            prov
                    );

                    lotes.add(lote);
                }
            }

        } catch (Exception e) {
            System.out.println("Error al buscar lotes por ID de proveedor: " + e.getMessage());
        }

        return lotes;
    }

}
