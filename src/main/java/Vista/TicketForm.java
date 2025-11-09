package Vista;

import Dominio.Ticket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TicketForm extends JFrame {
    private JPanel mainPanel;
    private JTable ticketTable;
    private JLabel clienteLabel;
    private JLabel totalLabel;
    private DefaultTableModel tableModel;

    public TicketForm(List<Ticket> tickets) {
        if (tickets == null || tickets.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No hay información de ticket disponible",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        inicializarComponentes();
        mostrarTicket(tickets);
    }

    private void inicializarComponentes() {
        setTitle("Ticket de Venta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel superior con información del cliente
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        clienteLabel = new JLabel();
        clienteLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalLabel = new JLabel();
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        infoPanel.add(clienteLabel);
        infoPanel.add(totalLabel);

        // Tabla de detalles
        String[] columnas = {"Medicamento", "Cantidad", "Precio Unitario", "Subtotal"};
        tableModel = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        ticketTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(ticketTable);

        // Panel de impresión
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton imprimirButton = new JButton("Imprimir");
        JButton cerrarButton = new JButton("Cerrar");
        buttonPanel.add(imprimirButton);
        buttonPanel.add(cerrarButton);

        imprimirButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "Funcionalidad de impresión pendiente de implementar",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        cerrarButton.addActionListener(e -> dispose());

        mainPanel.add(infoPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void mostrarTicket(List<Ticket> tickets) {
        if (tickets.isEmpty()) {
            return;
        }

        Ticket primerTicket = tickets.get(0);
        clienteLabel.setText(String.format("Cliente: %s - DNI: %d", 
                primerTicket.getNombreCliente(), 
                primerTicket.getDni()));
        
        float total = 0.0f;
        for (Ticket t : tickets) {
            tableModel.addRow(new Object[]{
                    t.getNombreMedicamento(),
                    t.getCantidad(),
                    String.format("$%.2f", t.getPrecio()),
                    String.format("$%.2f", t.getPrecio() * t.getCantidad())
            });
            total += t.getTotal();
        }
        
        totalLabel.setText(String.format("TOTAL: $%.2f", total));
    }
}

