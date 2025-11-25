package Vista;

import Controlador.AdministradorController;
import Datos.MedicamentoDAO;
import Dominio.Administrador;
import Dominio.Medicamento;
import Servicio.AdministradorService;
import Servicio.MedicamentoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;




public class MedicamentoForm extends JFrame {
    private JPanel mainPanel;
    private JTable medicamentosTable;
    private JTextField idTextField;
    private JTextField nombreTextField;
    private JTextField descripcionTextField;
    private JTextField precioTextField;
    private JTextField stockTextField;
    private JButton agregarButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JButton buscarButton;
    private JButton listarButton;
    private JButton limpiarButton;
    private JButton volverButton;

    private DefaultTableModel tableModel;
    private AdministradorController controller;

    public MedicamentoForm() {
        if (mainPanel == null) {
            mainPanel = new JPanel();
        }
        setContentPane(mainPanel);
        setTitle("Gestión de Medicamentos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        inicializarController();
        inicializarTabla();
        configurarEventos();
        listarMedicamentos();
    }

    private void inicializarController() {
        MedicamentoService medicamentoService = new MedicamentoService(new MedicamentoDAO());
        AdministradorService adminService = new AdministradorService(medicamentoService, null, null);
        controller = new AdministradorController(adminService);
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

    private void configurarEventos() {
        agregarButton.addActionListener(e -> agregarMedicamento());
        modificarButton.addActionListener(e -> modificarMedicamento());
        eliminarButton.addActionListener(e -> eliminarMedicamento());
        buscarButton.addActionListener(e -> buscarMedicamento());
        listarButton.addActionListener(e -> listarMedicamentos());
        limpiarButton.addActionListener(e -> limpiarCampos());
        volverButton.addActionListener(e -> dispose());

        medicamentosTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = medicamentosTable.getSelectedRow();
                if (row >= 0) {
                    idTextField.setText(tableModel.getValueAt(row, 0).toString());
                    nombreTextField.setText(tableModel.getValueAt(row, 1).toString());
                    descripcionTextField.setText(tableModel.getValueAt(row, 2).toString());
                    precioTextField.setText(tableModel.getValueAt(row, 3).toString());
                    stockTextField.setText(tableModel.getValueAt(row, 4).toString());
                }
            }
        });
    }

    private void agregarMedicamento() {
        try {
            int id = Integer.parseInt(idTextField.getText().trim());
            String nombre = nombreTextField.getText().trim();
            String descripcion = descripcionTextField.getText().trim();
            float precio = Float.parseFloat(precioTextField.getText().trim());
            int stock = Integer.parseInt(stockTextField.getText().trim());


            Medicamento medicamento = new Medicamento(id, nombre, descripcion, precio, stock);
            controller.agregarMedicamento(new Administrador(), medicamento);
            JOptionPane.showMessageDialog(this, "Medicamento agregado correctamente");
            limpiarCampos();
            listarMedicamentos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarMedicamento() {
        try {
            int id = Integer.parseInt(idTextField.getText().trim());
            int nuevoStock = Integer.parseInt(stockTextField.getText().trim());
            Medicamento medicamento = new Medicamento(id);
            controller.modificarMedicamento(new Administrador(), medicamento, nuevoStock);
            JOptionPane.showMessageDialog(this, "Stock modificado correctamente");
            limpiarCampos();
            listarMedicamentos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarMedicamento() {
        try {
            int id = Integer.parseInt(idTextField.getText().trim());
            int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro desea eliminar este medicamento?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                Medicamento medicamento = new Medicamento(id);
                controller.eliminarMedicamento(new Administrador(), medicamento);
                JOptionPane.showMessageDialog(this, "Medicamento eliminado correctamente");
                limpiarCampos();
                listarMedicamentos();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarMedicamento() {
        String nombre = nombreTextField.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un nombre para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Medicamento filtro = new Medicamento(nombre);
        List<Medicamento> resultados = new MedicamentoService(new MedicamentoDAO()).buscarMedicamentoPorNombre(filtro);
        tableModel.setRowCount(0);
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

    private void listarMedicamentos() {
        tableModel.setRowCount(0);
        List<Medicamento> medicamentos = new MedicamentoService(new MedicamentoDAO()).listarMedicamentos();
        for (Medicamento m : medicamentos) {
            tableModel.addRow(new Object[]{
                    m.getIdMedicamento(),
                    m.getNombre(),
                    m.getDescripcion(),
                    m.getPrecio(),
                    m.getStockTotal()
            });
        }
    }

    private void limpiarCampos() {
        idTextField.setText("");
        nombreTextField.setText("");
        descripcionTextField.setText("");
        precioTextField.setText("");
        stockTextField.setText("");
    }
}
