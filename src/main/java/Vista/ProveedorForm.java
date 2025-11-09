package Vista;

import Controlador.AdministradorController;
import Datos.ProveedorDAO;
import Dominio.Administrador;
import Dominio.Proveedor;
import Servicio.AdministradorService;
import Servicio.ProveedorService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ProveedorForm extends JFrame {
    private JPanel mainPanel;
    private JTable proveedoresTable;
    private JTextField idTextField;
    private JTextField razonSocialTextField;
    private JTextField dniTextField;
    private JTextField telefonoTextField;
    private JButton agregarButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JButton buscarButton;
    private JButton listarButton;
    private JButton limpiarButton;
    private DefaultTableModel tableModel;
    private AdministradorController controller;

    public ProveedorForm() {
        setContentPane(mainPanel);
        setTitle("Gestión de Proveedores");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        inicializarController();
        inicializarTabla();
        configurarEventos();
        listarProveedores();
    }

    private void inicializarController() {
        ProveedorService proveedorService = new ProveedorService(new ProveedorDAO());
        AdministradorService adminService = new AdministradorService(
                null,
                proveedorService,
                null
        );
        controller = new AdministradorController(adminService);
    }

    private void inicializarTabla() {
        String[] columnas = {"ID", "Razón Social", "DNI", "Teléfono", "Estado"};
        tableModel = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        proveedoresTable.setModel(tableModel);
        proveedoresTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void configurarEventos() {
        agregarButton.addActionListener(e -> agregarButtonActionPerformed());
        modificarButton.addActionListener(e -> modificarButtonActionPerformed());
        eliminarButton.addActionListener(e -> eliminarButtonActionPerformed());
        buscarButton.addActionListener(e -> buscarButtonActionPerformed());
        listarButton.addActionListener(e -> listarProveedores());
        limpiarButton.addActionListener(e -> limpiarCampos());

        proveedoresTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = proveedoresTable.getSelectedRow();
                if (row >= 0) {
                    idTextField.setText(tableModel.getValueAt(row, 0).toString());
                    razonSocialTextField.setText(tableModel.getValueAt(row, 1).toString());
                    dniTextField.setText(tableModel.getValueAt(row, 2).toString());
                    telefonoTextField.setText(tableModel.getValueAt(row, 3).toString());
                }
            }
        });
    }

    private void agregarButtonActionPerformed() {
        String id = idTextField.getText().trim();
        String razonSocial = razonSocialTextField.getText().trim();
        String dni = dniTextField.getText().trim();
        String telefono = telefonoTextField.getText().trim();

        if (id.isEmpty() || razonSocial.isEmpty() || dni.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, complete todos los campos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Proveedor proveedor = new Proveedor(id, razonSocial, dni, telefono);
        controller.agregarProveedor(new Administrador(), proveedor);
        JOptionPane.showMessageDialog(this, "Proveedor agregado correctamente");
        limpiarCampos();
        listarProveedores();
    }

    private void modificarButtonActionPerformed() {
        String id = idTextField.getText().trim();
        String razonSocial = razonSocialTextField.getText().trim();
        String dni = dniTextField.getText().trim();
        String telefono = telefonoTextField.getText().trim();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese un ID",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Proveedor proveedor = new Proveedor(id, razonSocial, dni, telefono);
        controller.modificarProveedor(new Administrador(), proveedor);
        JOptionPane.showMessageDialog(this, "Proveedor modificado correctamente");
        limpiarCampos();
        listarProveedores();
    }

    private void eliminarButtonActionPerformed() {
        String id = idTextField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese un ID",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de eliminar este proveedor?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );
        if (confirmacion == JOptionPane.YES_OPTION) {
            Proveedor proveedor = new Proveedor(id);
            controller.eliminarProveedor(new Administrador(), proveedor);
            JOptionPane.showMessageDialog(this, "Proveedor eliminado correctamente");
            limpiarCampos();
            listarProveedores();
        }
    }

    private void buscarButtonActionPerformed() {
        String id = idTextField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese un ID para buscar",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        Proveedor proveedor = new Proveedor(id);
        controller.buscarProveedorPorId(proveedor);
        if (proveedor.getRazonSocial() != null) {
            razonSocialTextField.setText(proveedor.getRazonSocial());
            dniTextField.setText(proveedor.getDni());
            telefonoTextField.setText(proveedor.getTelefono());
            JOptionPane.showMessageDialog(this, "Proveedor encontrado");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Proveedor no encontrado",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarProveedores() {
        tableModel.setRowCount(0);
        ProveedorService proveedorService = new ProveedorService(new ProveedorDAO());
        List<Proveedor> proveedores = proveedorService.listarProveedores();
        for (Proveedor p : proveedores) {
            tableModel.addRow(new Object[]{
                    p.getId(),
                    p.getRazonSocial(),
                    p.getDni(),
                    p.getTelefono(),
                    p.getEstado()
            });
        }
    }

    private void limpiarCampos() {
        idTextField.setText("");
        razonSocialTextField.setText("");
        dniTextField.setText("");
        telefonoTextField.setText("");
    }
}
