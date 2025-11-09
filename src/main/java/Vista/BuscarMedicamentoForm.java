package Vista;

import Controlador.EmpleadoController;
import Datos.*;
import Dominio.Medicamento;
import Servicio.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;

public class BuscarMedicamentoForm extends JFrame {
    private JPanel mainPanel;
    private JTable medicamentosTable;
    private JTextField nombreTextField;
    private JButton buscarButton;
    private DefaultTableModel tableModel;
    private EmpleadoController controller;

    public BuscarMedicamentoForm() {
        setContentPane(mainPanel);
        setTitle("Buscar Medicamento");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);

        inicializarController();
        inicializarTabla();
        buscarButton.addActionListener(this::buscarButtonActionPerformed);
        nombreTextField.addActionListener(e -> buscarButton.doClick());
    }

    private void inicializarController() {
        EmpleadoService empleadoService = new EmpleadoService(
                new UsuarioService(new ClienteDAO()),
                new DetalleVentaService(new DetalleVentaDAO()),
                new MedicamentoService(new MedicamentoDAO()),
                new VentaService(new VentaDAO()),
                new TicketService(new TicketDAO())
        );
        controller = new EmpleadoController(empleadoService);
    }

    private void inicializarTabla() {
        String[] columnas = {"ID", "Nombre", "Descripción", "Precio", "Stock"};
        tableModel = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        medicamentosTable.setModel(tableModel);
        medicamentosTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void buscarButtonActionPerformed(ActionEvent e) {
        String nombre = nombreTextField.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese un nombre para buscar",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Medicamento filtro = new Medicamento(nombre);
        List<Medicamento> resultados = controller.buscarMedicamentoPorNombre(filtro);

        tableModel.setRowCount(0);
        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No se encontraron medicamentos con ese nombre",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (Medicamento m : resultados) {
                tableModel.addRow(new Object[]{
                        m.getIdMedicamento(),
                        m.getNombre(),
                        m.getDescripcion(),
                        m.getPrecio(),
                        m.getStockTotal()
                });
            }
        }
    }
}
