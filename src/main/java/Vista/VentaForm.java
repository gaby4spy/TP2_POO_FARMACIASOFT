package Vista;

import Controlador.EmpleadoController;
import Datos.*;
import Dominio.*;
import Servicio.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.util.List;

public class VentaForm extends JFrame {
    private JPanel mainPanel;
    private JTextField buscarMedicamentoTextField;
    private JButton buscarMedicamentoButton;
    private JTable medicamentosTable;
    private JTable carritoTable;
    private JTextField idClienteTextField;
    private JTextField cantidadTextField;
    private JButton agregarCarritoButton;
    private JButton finalizarVentaButton;
    private JButton limpiarCarritoButton;
    private JButton volverButton;
    private JLabel totalLabel;
    private DefaultTableModel medicamentosTableModel;
    private DefaultTableModel carritoTableModel;
    private EmpleadoController controller;
    private float totalVenta = 0.0f;
    private int idEmpleado = 1;

    public VentaForm() {
        if (mainPanel == null) {
            mainPanel = new JPanel();
        }
        setContentPane(mainPanel);
        setTitle("Registrar Venta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        inicializarController();
        inicializarTablas();
        configurarEventos();
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

    private void inicializarTablas() {
        String[] columnasMed = {"ID", "Nombre", "Descripción", "Precio", "Stock"};
        medicamentosTableModel = new DefaultTableModel(columnasMed, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        medicamentosTable.setModel(medicamentosTableModel);
        medicamentosTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        String[] columnasCarrito = {"ID Medicamento", "Nombre", "Cantidad", "Precio Unitario", "Subtotal"};
        carritoTableModel = new DefaultTableModel(columnasCarrito, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        carritoTable.setModel(carritoTableModel);
    }

    private void configurarEventos() {
        if (buscarMedicamentoButton != null) {
            buscarMedicamentoButton.addActionListener(e -> buscarMedicamentos());
        }
        if (agregarCarritoButton != null) {
            agregarCarritoButton.addActionListener(e -> agregarAlCarrito());
        }
        if (finalizarVentaButton != null) {
            finalizarVentaButton.addActionListener(e -> finalizarVenta());
        }
        if (limpiarCarritoButton != null) {
            limpiarCarritoButton.addActionListener(e -> limpiarCarrito());
        }
        if (volverButton != null) {
            volverButton.addActionListener(e -> dispose());
        }
    }

    private void buscarMedicamentos() {
        String nombre = buscarMedicamentoTextField.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese un nombre para buscar",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Medicamento filtro = new Medicamento(nombre);
        List<Medicamento> resultados = controller.buscarMedicamentoPorNombre(filtro);

        medicamentosTableModel.setRowCount(0);
        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No se encontraron medicamentos con ese nombre",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (Medicamento m : resultados) {
                medicamentosTableModel.addRow(new Object[]{
                        m.getIdMedicamento(),
                        m.getNombre(),
                        m.getDescripcion(),
                        m.getPrecio(),
                        m.getStockTotal()
                });
            }
        }
    }

    private void agregarAlCarrito() {
        int selectedRow = medicamentosTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, seleccione un medicamento",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int idMedicamento = (Integer) medicamentosTableModel.getValueAt(selectedRow, 0);
            String nombre = (String) medicamentosTableModel.getValueAt(selectedRow, 1);
            float precio = (Float) medicamentosTableModel.getValueAt(selectedRow, 3);
            int stock = (Integer) medicamentosTableModel.getValueAt(selectedRow, 4);
            int cantidad = Integer.parseInt(cantidadTextField.getText().trim());

            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(this,
                        "La cantidad debe ser mayor a 0",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (cantidad > stock) {
                JOptionPane.showMessageDialog(this,
                        "No hay suficiente stock disponible",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            float subtotal = precio * cantidad;
            carritoTableModel.addRow(new Object[]{
                    idMedicamento,
                    nombre,
                    cantidad,
                    precio,
                    subtotal
            });

            totalVenta += subtotal;
            actualizarTotal();
            cantidadTextField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese una cantidad válida",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    private void finalizarVenta() {
        if (carritoTableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                    "El carrito está vacío",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int idCliente = Integer.parseInt(idClienteTextField.getText().trim());

            // 1️⃣ Crear venta
            Venta venta = new Venta(idEmpleado, idCliente, totalVenta);

            // 2️⃣ Registrar la venta primero
            boolean exitoVenta = controller.registrarVenta(venta);

            if (!exitoVenta) {
                JOptionPane.showMessageDialog(this,
                        "Error al registrar la venta",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 3️⃣ Obtener el último idVenta
            int idVenta = controller.obtenerUltimoIdVenta();
            venta.setIdVenta(idVenta);

            // 4️⃣ Registrar los detalles
            for (int i = 0; i < carritoTableModel.getRowCount(); i++) {
                int idMedicamento = (Integer) carritoTableModel.getValueAt(i, 0);
                int cantidad = (Integer) carritoTableModel.getValueAt(i, 2);
                float precioUnitario = (Float) carritoTableModel.getValueAt(i, 3);
                float subtotal = (Float) carritoTableModel.getValueAt(i, 4);

                int idLote = 1; // temporal

                DetalleVenta detalle = new DetalleVenta(
                        idVenta,
                        idLote,
                        cantidad,
                        precioUnitario,
                        subtotal,
                        java.time.LocalDateTime.now()
                );

                controller.registrarDetalle(detalle);
            }

            // 5️⃣ Mostrar ticket
            int opcion = JOptionPane.showConfirmDialog(this,
                    "Venta registrada correctamente. ¿Desea generar el ticket?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {
                List<Ticket> tickets = controller.generarTicket(venta);
                TicketForm ticketForm = new TicketForm(tickets);
                ticketForm.setVisible(true);
            }

            // 6️⃣ Limpiar
            limpiarCarrito();
            idClienteTextField.setText("");
            buscarMedicamentoTextField.setText("");
            medicamentosTableModel.setRowCount(0);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese un ID de cliente válido",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    private int obtenerUltimoIdVenta() {
        try {
            java.sql.Connection con = BD.Conexion.getConexion();
            java.sql.Statement stmt = con.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("SELECT MAX(idVenta) as maxId FROM Venta");
            if (rs.next()) {
                return rs.getInt("maxId");
            }
        } catch (Exception e) {
            System.out.println("Error al obtener último ID: " + e.getMessage());
        }
        return 0;
    }

    private void limpiarCarrito() {
        carritoTableModel.setRowCount(0);
        totalVenta = 0.0f;
        actualizarTotal();
    }

    private void actualizarTotal() {
        totalLabel.setText(String.format("Total: $%.2f", totalVenta));
    }
}
