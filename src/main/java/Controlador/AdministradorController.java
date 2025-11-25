package Controlador;


import Dominio.Administrador;
import Dominio.Cliente;
import Dominio.Medicamento;
import Dominio.Proveedor;
import Servicio.AdministradorService;

import java.util.List;

public class AdministradorController {

    private AdministradorService adminService;

    public AdministradorController(AdministradorService adminService) {
        this.adminService = adminService;
    }

    // =========================================================
    // MÉTODOS RELACIONADOS CON MEDICAMENTOS
    // =========================================================

    public void agregarMedicamento(Administrador admin, Medicamento medicamento) {
        if (admin == null) {
            System.out.println("Error: usuario no autorizado.");
            return;
        }
        boolean exito = adminService.agregarMedicamento(medicamento);
        System.out.println(exito ? "Medicamento agregado correctamente" : "Error al agregar medicamento");
    }

    public void eliminarMedicamento(Administrador admin, Medicamento medicamento) {
        if (admin == null) {
            System.out.println("Error: usuario no autorizado.");
            return;
        }
        boolean exito = adminService.eliminarMedicamentoComoAdmin(admin, medicamento);
        System.out.println(exito ? "Medicamento eliminado correctamente" : "Error al eliminar medicamento");
    }

    public void modificarMedicamento(Administrador admin, Medicamento medicamento, int nuevoStock) {
        if (admin == null) {
            System.out.println("Error: usuario no autorizado.");
            return;
        }
        boolean exito = adminService.modificarMedicamento(medicamento, nuevoStock);
        System.out.println(exito ? "Stock modificado correctamente" : "Error al modificar stock");
    }

    public void listarMedicamentos() {
        List<Medicamento> lista = adminService.verMedicamentos();
        if (lista.isEmpty()) {
            System.out.println("No hay medicamentos registrados.");
        } else {
            System.out.println("=== LISTA DE MEDICAMENTOS ===");
            lista.forEach(System.out::println);
        }
    }

    public void buscarMedicamentoPorNombre(Medicamento filtro) {
        List<Medicamento> resultados = adminService.buscarMedicamentoPorNombre(filtro);
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron medicamentos con ese nombre.");
        } else {
            resultados.forEach(System.out::println);
        }
    }

    // =========================================================
    // MÉTODOS RELACIONADOS CON PROVEEDORES
    // =========================================================

    public void agregarProveedor(Administrador admin, Proveedor proveedor) {
        if (admin == null) {
            System.out.println("Error: usuario no autorizado.");
            return;
        }
        boolean exito = adminService.agregarProveedor(proveedor);
        System.out.println(exito ? "Proveedor agregado correctamente" : "Error al agregar proveedor");
    }

    public void modificarProveedor(Administrador admin, Proveedor proveedor) {
        if (admin == null) {
            System.out.println("Error: usuario no autorizado.");
            return;
        }
        boolean exito = adminService.modificarProveedor(proveedor);
        System.out.println(exito ? "Proveedor modificado correctamente" : "Error al modificar proveedor");
    }

    public void eliminarProveedor(Administrador admin, Proveedor proveedor) {
        if (admin == null) {
            System.out.println("Error: usuario no autorizado.");
            return;
        }
        boolean exito = adminService.eliminarProveedor(proveedor);
        System.out.println(exito ? "Proveedor eliminado correctamente" : "Error al eliminar proveedor");
    }

    public void listarProveedores() {
        List<Proveedor> lista = adminService.listarProveedores();
        if (lista.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
        } else {
            System.out.println("=== LISTA DE PROVEEDORES ===");
            lista.forEach(System.out::println);
        }
    }

    public void buscarProveedorPorId(Proveedor proveedor) {
        boolean encontrado = adminService.buscarProveedorPorId(proveedor);
        System.out.println(encontrado ? "Proveedor encontrado: " + proveedor : "Proveedor no encontrado");
    }

    // =========================================================
    // MÉTODOS RELACIONADOS CON CLIENTES/USUARIOS
    // =========================================================

    public void agregarCliente(Administrador admin, Cliente cliente) {
        if (admin == null) {
            System.out.println("Error: usuario no autorizado.");
            return;
        }
        boolean exito = adminService.agregarCliente(cliente);
        System.out.println(exito ? "Cliente agregado correctamente" : "Error al agregar cliente");
    }

    public void modificarCliente(Administrador admin, Cliente cliente) {
        if (admin == null) {
            System.out.println("Error: usuario no autorizado.");
            return;
        }
        boolean exito = adminService.modificarCliente(cliente);
        System.out.println(exito ? "Cliente modificado correctamente" : "Error al modificar cliente");
    }

    public void eliminarCliente(Administrador admin, Cliente cliente) {
        if (admin == null) {
            System.out.println("Error: usuario no autorizado.");
            return;
        }
        boolean exito = adminService.eliminarCliente(cliente);
        System.out.println(exito ? "Cliente eliminado correctamente" : "Error al eliminar cliente");
    }

    public void buscarClientePorId(Cliente cliente) {
        boolean encontrado = adminService.buscarClientePorId(cliente);
        System.out.println(encontrado ? "Cliente encontrado: " + cliente : "Cliente no encontrado");
    }
}