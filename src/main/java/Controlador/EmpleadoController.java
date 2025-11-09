package Controlador;

import Dominio.*;
import Servicio.EmpleadoService;

import java.util.List;



public class EmpleadoController {
    private EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    public List<Medicamento> buscarMedicamentoPorNombre(Medicamento medicamento) {
        return empleadoService.buscarMedicamentoPorNombre(medicamento);
    }

    public boolean agregarCliente(Cliente cliente) {
        return empleadoService.agregarCliente(cliente);
    }

    // 🔹 Registrar venta sola
    public boolean registrarVenta(Venta venta) {
        return empleadoService.registrarVenta(venta);
    }

    // 🔹 Registrar detalle después


    public List<Ticket> generarTicket(Venta venta) {
        return empleadoService.generarTicket(venta);
    }


    public boolean registrarDetalle(DetalleVenta detalleVenta) {
        return empleadoService.registrarDetalle(detalleVenta);
    }

    public int obtenerUltimoIdVenta() {
        return empleadoService.obtenerUltimoIdVenta();
    }

}
