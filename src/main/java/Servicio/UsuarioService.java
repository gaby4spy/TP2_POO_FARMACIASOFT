package Servicio;

import Datos.IClienteDAO;
import Dominio.Cliente;

import java.util.List;

public class UsuarioService {
    private IClienteDAO clienteDAO;

    public UsuarioService(IClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;

    }

   public List<Cliente> listarClientes (){
        return clienteDAO.listarClientes();
   };
   public boolean buscarClientePorId(Cliente cliente){
       return clienteDAO.buscarClientePorId(cliente);
   };
   public boolean agregarCliente (Cliente cliente){
       return clienteDAO.agregarCliente(cliente);
   };
   public boolean modificarCliente( Cliente cliente){
       return clienteDAO.modificarCliente(cliente);
   };
   public boolean eliminarCliente (Cliente cliente){
       return clienteDAO.eliminarCliente(cliente);
   };

}
