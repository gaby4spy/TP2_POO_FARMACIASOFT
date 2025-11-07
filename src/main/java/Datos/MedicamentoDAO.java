package Datos;


import BD.Conexion;
import Dominio.Medicamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAO implements IMedicamentoDAO {

    @Override
    public List<Medicamento> listarMedicamento() {
        List<Medicamento> medicamentos = new ArrayList<>();
        String sql = "SELECT * FROM Medicamento";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Medicamento med = new Medicamento(
                        rs.getInt("idMedicamento"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getFloat("precio"),
                        rs.getInt("stockTotal")
                );
                medicamentos.add(med);
            }

        } catch (Exception e) {
            System.out.println("Error al listar medicamentos: " + e.getMessage());
        }

        return medicamentos;
    }

    @Override
    public List<Medicamento> buscarMedicamentoPorNombre(Medicamento medicamento) {
        List<Medicamento> medicamentos = new ArrayList<>();
        String sql = "SELECT * FROM Medicamento WHERE nombre LIKE ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + medicamento.getNombre() + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Medicamento med = new Medicamento(
                            rs.getInt("idMedicamento"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getFloat("precio"),
                            rs.getInt("stockTotal")
                    );
                    medicamentos.add(med);
                }
            }

        } catch (Exception e) {
            System.out.println("Error al buscar medicamento: " + e.getMessage());
        }

        return medicamentos;
    }

    @Override
    public boolean agregarMedicamento(Medicamento medicamento) {
        String sql = "INSERT INTO Medicamento (idMedicamento, nombre, descripcion, precio, stockTotal, categoria) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, medicamento.getIdMedicamento());
            ps.setString(2, medicamento.getNombre());
            ps.setString(3, medicamento.getDescripcion());
            ps.setFloat(4, medicamento.getPrecio());
            ps.setInt(5, medicamento.getStockTotal());


            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error al agregar medicamento: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean modificarMedicamento(Medicamento medicamento , int stockNuevo) {
        String sql = "UPDATE Medicamento SET stockTotal = ? WHERE idMedicamento = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, stockNuevo);
            ps.setInt(2, medicamento.getIdMedicamento());
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error al modificar medicamento: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean eliminarMedicamento(Medicamento medicamento) {
        String sql = "DELETE FROM Medicamento WHERE idMedicamento = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, medicamento.getIdMedicamento());
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error al eliminar medicamento: " + e.getMessage());
        }

        return false;
    }
}
