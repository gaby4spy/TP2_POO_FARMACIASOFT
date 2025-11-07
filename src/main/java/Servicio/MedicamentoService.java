package Servicio;

import Datos.IMedicamentoDAO;
import Dominio.Medicamento;
import java.util.List;

public class MedicamentoService {
    private IMedicamentoDAO medicamentoDAO;

    public MedicamentoService(IMedicamentoDAO dao) {
        this.medicamentoDAO = dao;
    }

    public boolean eliminarMedicamento(Medicamento medicamento) {
        // Aquí podrías validar reglas de negocio antes de eliminar
        return medicamentoDAO.eliminarMedicamento(medicamento);
    }

    public List<Medicamento> listarMedicamentos() {
        return medicamentoDAO.listarMedicamento();
    }

    public  List<Medicamento> buscarMedicamentoPorNombre(Medicamento medicamento){

        return medicamentoDAO.buscarMedicamentoPorNombre(medicamento);
    }

    public boolean modificarMedicamento(Medicamento medicamento , int stockNuevo){
        return  medicamentoDAO.modificarMedicamento(medicamento,stockNuevo);
    }
    public boolean agregarMedicamento(Medicamento medicamento){
        return medicamentoDAO.agregarMedicamento(medicamento);
    }
}
