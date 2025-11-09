package Servicio;

import Datos.IDetalleVentaDAO;
import Dominio.DetalleVenta;

public class DetalleVentaService {
    private IDetalleVentaDAO detalleVentaDAO;


    public DetalleVentaService(IDetalleVentaDAO detalleVentaDAO) {
        this.detalleVentaDAO = detalleVentaDAO;
    }

    public   boolean agregarDetalleVenta(DetalleVenta detalle){
        return detalleVentaDAO.agregarDetalleVenta(detalle);
    };

}
