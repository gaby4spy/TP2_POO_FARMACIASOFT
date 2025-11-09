package Vista;

import Datos.*;
import Servicio.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;

public class ReportesForm extends JFrame {
    private JPanel mainPanel;
    private JTable reporteTable;
    private JComboBox<String> tipoReporteCombo;
    private JButton generarButton;
    private DefaultTableModel tableModel;

    public ReportesForm() {
        setContentPane(mainPanel);
        setTitle("Generar Reportes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);

        inicializarTabla();
        generarButton.addActionListener(this::generarButtonActionPerformed);
    }

    private void inicializarTabla() {
        String[] columnas = {"Columna 1", "Columna 2", "Columna 3", "Columna 4"};
        tableModel = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        reporteTable.setModel(tableModel);
    }

    private void generarButtonActionPerformed(ActionEvent e) {
        String tipoReporte = (String) tipoReporteCombo.getSelectedItem();
        generarReporte(tipoReporte);
    }

    private void generarReporte(String tipo) {
        tableModel.setRowCount(0);

        switch (tipo) {
            case "Medicamentos":
                generarReporteMedicamentos();
                break;
            case "Proveedores":
                generarReporteProveedores();
                break;
            case "Clientes":
                generarReporteClientes();
                break;
            case "Ventas":
                generarReporteVentas();
                break;
        }
    }

    private void generarReporteMedicamentos() {
        String[] columnas = {"ID", "Nombre", "Descripción", "Precio", "Stock"};
        tableModel.setColumnIdentifiers(columnas);
        MedicamentoService medicamentoService = new MedicamentoService(new MedicamentoDAO());
        medicamentoService.listarMedicamentos().forEach(m -> {
            tableModel.addRow(new Object[]{
                    m.getIdMedicamento(),
                    m.getNombre(),
                    m.getDescripcion(),
                    m.getPrecio(),
                    m.getStockTotal()
            });
        });
    }

    private void generarReporteProveedores() {
        String[] columnas = {"ID", "Razón Social", "DNI", "Teléfono", "Estado"};
        tableModel.setColumnIdentifiers(columnas);
        ProveedorService proveedorService = new ProveedorService(new ProveedorDAO());
        proveedorService.listarProveedores().forEach(p -> {
            tableModel.addRow(new Object[]{
                    p.getId(),
                    p.getRazonSocial(),
                    p.getDni(),
                    p.getTelefono(),
                    p.getEstado()
            });
        });
    }

    private void generarReporteClientes() {
        String[] columnas = {"ID", "Nombre", "DNI", "Teléfono"};
        tableModel.setColumnIdentifiers(columnas);
        UsuarioService usuarioService = new UsuarioService(new ClienteDAO());
        usuarioService.listarClientes().forEach(c -> {
            tableModel.addRow(new Object[]{
                    c.getId(),
                    c.getNombre(),
                    c.getDni(),
                    c.getTelefono()
            });
        });
    }

    private void generarReporteVentas() {
        String[] columnas = {"ID Venta", "ID Empleado", "ID Cliente", "Total"};
        tableModel.setColumnIdentifiers(columnas);
        JOptionPane.showMessageDialog(this,
                "Funcionalidad de reporte de ventas pendiente de implementar",
                "Información",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
