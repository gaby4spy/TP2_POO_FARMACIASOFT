package Servicio;

import Dominio.*;

import java.util.List;


public class EmpleadoService {
        private UsuarioService usuarioService;
        private MedicamentoService medicamentoService;
        private VentaService ventaService;
        private DetalleVentaService detalleVentaService;
        private TicketService ticketService;

        public EmpleadoService(
                UsuarioService usuarioService,
                DetalleVentaService detalleVentaService,
                MedicamentoService medicamentoService,
                VentaService ventaService,
                TicketService ticketService
        ) {
            this.usuarioService = usuarioService;
            this.detalleVentaService = detalleVentaService;
            this.medicamentoService = medicamentoService;
            this.ventaService = ventaService;
            this.ticketService = ticketService;
        }

        public List<Medicamento> buscarMedicamentoPorNombre(Medicamento medicamento) {
            return medicamentoService.buscarMedicamentoPorNombre(medicamento);
        }

        public boolean agregarCliente(Cliente cliente) {
            return usuarioService.agregarCliente(cliente);
        }


        public boolean registrarVenta(Venta venta) {
            return ventaService.registrarVenta(venta);
        }

        public boolean registrarDetalle(DetalleVenta detalleVenta) {
            return detalleVentaService.agregarDetalleVenta(detalleVenta);
        }

        public List<Ticket> generarTicket(Venta venta) {
            return ticketService.generarTicket(venta);
        }

      public int obtenerUltimoIdVenta() {
        return ventaService.obtenerUltimoIdVenta();
    }

}