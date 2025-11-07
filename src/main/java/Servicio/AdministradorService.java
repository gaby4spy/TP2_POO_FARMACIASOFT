package Servicio;


import Dominio.Administrador;
import Dominio.Medicamento;
import java.util.List;

public class AdministradorService {
    private MedicamentoService medicamentoService;

    public AdministradorService(MedicamentoService medService) {
        this.medicamentoService = medService;
    }

    public boolean eliminarMedicamentoComoAdmin(Administrador admin, Medicamento med) {
        // Validar que el usuario es administrador
        if (admin == null) return false;

        // Delegamos la eliminación al MedicamentoService
        return medicamentoService.eliminarMedicamento(med);
    }

    public List<Medicamento> verMedicamentos() {
        return medicamentoService.listarMedicamentos();
    }


    public List<Medicamento> buscarMedicamentoPorNombre(Medicamento medicamento){
        return medicamentoService.buscarMedicamentoPorNombre(medicamento);
    }

    public boolean modificarMedicamento(Medicamento medicamento , int stockNuevo){
        return medicamentoService.modificarMedicamento(medicamento,stockNuevo);
    }

    public boolean agregarMedicamento(Medicamento medicamento){
        return medicamentoService.agregarMedicamento(medicamento);
    }

}
