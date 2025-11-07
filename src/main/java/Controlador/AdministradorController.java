package Controlador;


import Dominio.Administrador;
import Dominio.Medicamento;
import Servicio.AdministradorService;

import java.util.List;

public class AdministradorController {
    private AdministradorService adminService;

    public AdministradorController(AdministradorService service) {
        this.adminService = service;
    }

    // Método para listar medicamentos
    public List<Medicamento> verMedicamentos(Administrador admin) {
        // Si querés, validás permisos del admin aquí
        return adminService.verMedicamentos();
    }

    public void eliminarMedicamento(Administrador admin, Medicamento med) {
        boolean eliminado = adminService.eliminarMedicamentoComoAdmin(admin, med);
        if(eliminado) {
            System.out.println("Medicamento eliminado correctamente");
        } else {
            System.out.println("No se pudo eliminar el medicamento");
        }
    }

    public List<Medicamento> buscarMedicamentoPorNombre(Medicamento medicamento){
        return adminService.buscarMedicamentoPorNombre(medicamento);
    }

    public boolean modificarMedicamento(Medicamento medicamento , int stockNuevo){
        return adminService.modificarMedicamento(medicamento,stockNuevo);
    }

    public boolean agregarMedicamento(Medicamento medicamento){
        return adminService.agregarMedicamento(medicamento);
    }



}
