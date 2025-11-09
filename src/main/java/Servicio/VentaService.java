package Servicio;

import Datos.IVentaDAO;
import Dominio.Venta;

public class VentaService {
    private IVentaDAO ventaDAO;

    public VentaService(IVentaDAO ventaDAO) {
        this.ventaDAO = ventaDAO;
    }

    public boolean registrarVenta(Venta venta){

        return ventaDAO.registrarVenta(venta);
    };
}
