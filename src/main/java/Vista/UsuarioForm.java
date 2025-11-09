package Vista;



import Controlador.AdministradorController;
import Datos.*;
import Dominio.Administrador;
import Dominio.Cliente;
import Servicio.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class UsuarioForm extends JFrame {
    private JPanel mainPanel;
    private JTable usuariosTable;
    private JTextField idTextField;
    private JTextField nombreTextField;
    private JTextField dniTextField;
    private JTextField telefonoTextField;
    private JButton agregarButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JButton buscarButton;
    private JButton listarButton;
    private JButton limpiarButton;
    private JButton volverButton;
    private DefaultTableModel tableModel;
    private AdministradorController controller;

    // 🔹 Constructor principal
    public UsuarioForm() {
        initComponents(); // ✅ Inicializa todos los componentes

        setContentPane(mainPanel);
        setTitle("Gestión de Usuarios/Clientes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        inicializarController();
        inicializarTabla();
        configurarEventos();
        listarUsuarios();
    }

    // 🔹 Crea los componentes Swing (si no usás diseñador)
    private void initComponents() {
        mainPanel = new JPanel(new BorderLayout(10, 10));

        // Campos de texto
        idTextField = new JTextField(10);
        nombreTextField = new JTextField(15);
        dniTextField = new JTextField(10);
        telefonoTextField = new JTextField(10);

        // Botones
        agregarButton = new JButton("Agregar");
        modificarButton = new JButton("Modificar");
        eliminarButton = new JButton("Eliminar");
        buscarButton = new JButton("Buscar");
        listarButton = new JButton("Listar");
        limpiarButton = new JButton("Limpiar");
        volverButton = new JButton("Volver");

        // Tabla
        usuariosTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(usuariosTable);

        // Panel superior (formulario)
        JPanel formPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        formPanel.add(new JLabel("ID:"));
        formPanel.add(idTextField);
        formPanel.add(buscarButton);
        formPanel.add(new JLabel(""));
        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(nombreTextField);
        formPanel.add(new JLabel("DNI:"));
        formPanel.add(dniTextField);
        formPanel.add(new JLabel("Teléfono:"));
        formPanel.add(telefonoTextField);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(agregarButton);
        buttonPanel.add(modificarButton);
        buttonPanel.add(eliminarButton);
        buttonPanel.add(listarButton);
        buttonPanel.add(limpiarButton);
        buttonPanel.add(volverButton);

        // Agregamos todo al panel principal
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void inicializarController() {
        UsuarioService usuarioService = new UsuarioService(new ClienteDAO());
        AdministradorService adminService = new AdministradorService(
                new MedicamentoService(new MedicamentoDAO()),
                new ProveedorService(new ProveedorDAO()),
                usuarioService
        );
        controller = new AdministradorController(adminService);
    }

    private void inicializarTabla() {
        String[] columnas = {"ID", "Nombre", "DNI", "Teléfono"};
        tableModel = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        usuariosTable.setModel(tableModel);
        usuariosTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void configurarEventos() {
        agregarButton.addActionListener(e -> agregarButtonActionPerformed());
        modificarButton.addActionListener(e -> modificarButtonActionPerformed());
        eliminarButton.addActionListener(e -> eliminarButtonActionPerformed());
        buscarButton.addActionListener(e -> buscarButtonActionPerformed());
        listarButton.addActionListener(e -> listarUsuarios());
        limpiarButton.addActionListener(e -> limpiarCampos());
        volverButton.addActionListener(e -> dispose());

        usuariosTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = usuariosTable.getSelectedRow();
                if (row >= 0) {
                    idTextField.setText(tableModel.getValueAt(row, 0).toString());
                    nombreTextField.setText(tableModel.getValueAt(row, 1).toString());
                    dniTextField.setText(tableModel.getValueAt(row, 2).toString());
                    telefonoTextField.setText(tableModel.getValueAt(row, 3).toString());
                }
            }
        });
    }

    private void agregarButtonActionPerformed() {
        String nombre = nombreTextField.getText().trim();
        String dni = dniTextField.getText().trim();
        String telefono = telefonoTextField.getText().trim();

        if (nombre.isEmpty() || dni.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, complete todos los campos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = new Cliente(nombre, dni, telefono);
        controller.agregarCliente(new Administrador(), cliente);
        JOptionPane.showMessageDialog(this, "Cliente agregado correctamente");
        limpiarCampos();
        listarUsuarios();
    }

    private void modificarButtonActionPerformed() {
        try {
            int id = Integer.parseInt(idTextField.getText().trim());
            String nombre = nombreTextField.getText().trim();
            String dni = dniTextField.getText().trim();
            String telefono = telefonoTextField.getText().trim();

            if (nombre.isEmpty() || dni.isEmpty() || telefono.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Por favor, complete todos los campos",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Cliente cliente = new Cliente(id, nombre, dni, telefono);
            controller.modificarCliente(new Administrador(), cliente);
            JOptionPane.showMessageDialog(this, "Cliente modificado correctamente");
            limpiarCampos();
            listarUsuarios();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese un ID válido",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarButtonActionPerformed() {
        try {
            int id = Integer.parseInt(idTextField.getText().trim());
            int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro de eliminar este cliente?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirmacion == JOptionPane.YES_OPTION) {
                Cliente cliente = new Cliente(id);
                controller.eliminarCliente(new Administrador(), cliente);
                JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente");
                limpiarCampos();
                listarUsuarios();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese un ID válido",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarButtonActionPerformed() {
        try {
            int id = Integer.parseInt(idTextField.getText().trim());
            Cliente cliente = new Cliente(id);
            controller.buscarClientePorId(cliente);
            if (cliente.getNombre() != null) {
                nombreTextField.setText(cliente.getNombre());
                dniTextField.setText(cliente.getDni());
                telefonoTextField.setText(cliente.getTelefono());
                JOptionPane.showMessageDialog(this, "Cliente encontrado");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Cliente no encontrado",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese un ID válido",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarUsuarios() {
        tableModel.setRowCount(0);
        UsuarioService usuarioService = new UsuarioService(new ClienteDAO());
        List<Cliente> clientes = usuarioService.listarClientes();
        for (Cliente c : clientes) {
            tableModel.addRow(new Object[]{
                    c.getId(),
                    c.getNombre(),
                    c.getDni(),
                    c.getTelefono()
            });
        }
    }

    private void limpiarCampos() {
        idTextField.setText("");
        nombreTextField.setText("");
        dniTextField.setText("");
        telefonoTextField.setText("");
    }

    // 🔹 Para probar este formulario directamente:
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UsuarioForm().setVisible(true));
    }
}
