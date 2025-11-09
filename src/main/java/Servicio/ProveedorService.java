package Servicio;

import Datos.IProveedorDAO;
import Dominio.Proveedor;

import java.util.List;

public class ProveedorService {
    private IProveedorDAO proveedorDAO;

    public ProveedorService(IProveedorDAO provDao) {
        this.proveedorDAO=provDao;
    }


    public  List<Proveedor> listarProveedores () {

        return proveedorDAO.listarProveedores();
    };

    public boolean buscarProveedorPorId(Proveedor proveedor){
        return proveedorDAO.buscarProveedorPorId(proveedor);
    };

    public  boolean agregarProveedor (Proveedor proveedor){
        return proveedorDAO.agregarProveedor(proveedor);
    };


    public  boolean modificarProveedor( Proveedor proveedor){
        return proveedorDAO.modificarProveedor(proveedor);
    };
    public  boolean eliminarProveedor (Proveedor proveedor){
        return proveedorDAO.eliminarProveedor(proveedor);
    };
}

