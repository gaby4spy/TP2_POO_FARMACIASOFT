package Servicio;

import Dominio.Administrador;
import Dominio.Cliente;
import Dominio.Medicamento;
import Dominio.Proveedor;

import java.util.List;

public class AdministradorService {
    private MedicamentoService medicamentoService;
    private ProveedorService proveedorService;
    private UsuarioService usuarioService;

    public AdministradorService(MedicamentoService medService, ProveedorService provService,UsuarioService usuarioService) {
        this.medicamentoService = medService;
        this.proveedorService = provService;
        this.usuarioService=usuarioService;
    }

    // --- Métodos de medicamentos ---
    public boolean eliminarMedicamentoComoAdmin(Administrador admin, Medicamento med) {
        if (admin == null) return false;
        return medicamentoService.eliminarMedicamento(med);
    }

    public List<Medicamento> verMedicamentos() {
        return medicamentoService.listarMedicamentos();
    }

    public List<Medicamento> buscarMedicamentoPorNombre(Medicamento medicamento){
        return medicamentoService.buscarMedicamentoPorNombre(medicamento);
    }

    public boolean modificarMedicamento(Medicamento medicamento , int stockNuevo){
        return medicamentoService.modificarMedicamento(medicamento, stockNuevo);
    }

    public boolean agregarMedicamento(Medicamento medicamento){
        return medicamentoService.agregarMedicamento(medicamento);
    }

    // --- Métodos de proveedores ---
    public List<Proveedor> listarProveedores () {
        return proveedorService.listarProveedores();
    }

    public boolean buscarProveedorPorId(Proveedor proveedor) {
        return proveedorService.buscarProveedorPorId(proveedor);
    }

    public boolean agregarProveedor (Proveedor proveedor) {
        return proveedorService.agregarProveedor(proveedor);
    }

    public boolean modificarProveedor(Proveedor proveedor) {
        return proveedorService.modificarProveedor(proveedor);
    }

    public boolean eliminarProveedor (Proveedor proveedor) {
        return proveedorService.eliminarProveedor(proveedor);
    }


    // metodos clientes

    public List<Cliente> listarClientes (){
        return usuarioService.listarClientes();
    };
    public boolean buscarClientePorId(Cliente cliente){
        return usuarioService.buscarClientePorId(cliente);
    };
    public boolean agregarCliente (Cliente cliente){
        return usuarioService.agregarCliente(cliente);
    };
    public boolean modificarCliente( Cliente cliente){
        return usuarioService.modificarCliente(cliente);
    };
    public boolean eliminarCliente (Cliente cliente){
        return usuarioService.eliminarCliente(cliente);
    };


}
