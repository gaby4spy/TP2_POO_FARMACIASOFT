package Controlador;

import Dominio.*;
import Servicio.EmpleadoService;

import java.util.List;

public class EmpleadoController {
    private EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    public List<Medicamento> buscarMedicamentoPorNombre(Medicamento medicamento){
        return empleadoService.buscarMedicamentoPorNombre(medicamento);
    }

    public boolean agregarCliente (Cliente cliente){
        return empleadoService.agregarCliente(cliente);
    }

    public boolean registrarVenta(Venta venta, DetalleVenta detalleVenta){
        return empleadoService.registrarVenta(venta,detalleVenta);
    }


    public List<Ticket> generarTicket(Venta venta){
        return empleadoService.generarTicket(venta);
    };


}
