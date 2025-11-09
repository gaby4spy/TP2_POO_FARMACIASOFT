package Datos;

import Dominio.Ticket;
import Dominio.Venta;

import java.util.List;

public interface ITicketDAO {
    List<Ticket> generarTicket(Venta venta);
}
