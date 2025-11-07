package Datos;

import Dominio.Medicamento;

import java.util.List;

public interface IMedicamentoDAO {
    List<Medicamento> listarMedicamento();
    List<Medicamento> buscarMedicamentoPorNombre(Medicamento medicamento);
    boolean agregarMedicamento(Medicamento medicamento);
    boolean modificarMedicamento(Medicamento medicamento, int cantidad);
    boolean eliminarMedicamento(Medicamento medicamento);
}
