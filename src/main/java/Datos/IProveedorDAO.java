package Datos;

import Dominio.Proveedor;

import java.util.List;

public interface IProveedorDAO {
    List<Proveedor> listarProveedores ();
    boolean buscarProveedorPorId(Proveedor proveedor);
    boolean agregarProveedor (Proveedor proveedor);
    boolean modificarProveedor( Proveedor proveedor);
    boolean eliminarProveedor (Proveedor proveedor);

}
