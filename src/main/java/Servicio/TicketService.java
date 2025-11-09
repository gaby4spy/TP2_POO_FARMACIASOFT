package Servicio;

import Datos.ITicketDAO;
import Dominio.Ticket;
import Dominio.Venta;

import java.util.List;

public class TicketService {
    private ITicketDAO ticket;

    public TicketService(ITicketDAO ticket) {
        this.ticket= ticket;
    }


    public  List<Ticket> generarTicket(Venta venta){
        return ticket.generarTicket(venta);
    };
}
