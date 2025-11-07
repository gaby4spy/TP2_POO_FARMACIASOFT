package Datos;

import Dominio.Cliente;

import java.util.List;

public interface IClienteDAO {
    List<Cliente> listarClientes ();
    boolean buscarClientePorId(Cliente cliente);
    boolean agregarCliente (Cliente cliente);
    boolean modificarCliente( Cliente cliente);
    boolean eliminarCliente (Cliente cliente);

}
