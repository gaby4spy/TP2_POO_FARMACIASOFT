package Datos;

import Dominio.LoteMedicamento;

import java.util.List;

public interface ILoteMedicamentoDAO {
    /*Esta interfaz solo va  a permitir añadir lotes pero no hacer el crud por completos ademas de la
    * manipulacion de datos*/

    // Inserta un nuevo lote en la base de datos
    boolean agregarLote(LoteMedicamento lote);
    // Devuelve todos los lotes registrados
    List<LoteMedicamento> listarLotes();
    // Busca los lotes por nombre del medicamento (útil para reportes)
    List<LoteMedicamento> buscarPorMedicamento(String nombreMedicamento);
    // Busca los lotes por proveedor
    List<LoteMedicamento> buscarPorProveedor(String nombreProveedor);


}
