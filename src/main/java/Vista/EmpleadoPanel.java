package Vista;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EmpleadoPanel extends JFrame {
    private JPanel mainPanel;
    private JButton registrarClienteButton;
    private JButton registrarVentaButton;
    private JButton buscarMedicamentoButton;
    private JButton salirButton;
    private JLabel tituloLabel;

    public EmpleadoPanel() {
        setContentPane(mainPanel);
        setTitle("FarmaciaSoft - Panel de Empleado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        registrarClienteButton.addActionListener(this::registrarClienteButtonActionPerformed);
        registrarVentaButton.addActionListener(this::registrarVentaButtonActionPerformed);
        buscarMedicamentoButton.addActionListener(this::buscarMedicamentoButtonActionPerformed);
        salirButton.addActionListener(this::salirButtonActionPerformed);
    }

    private void registrarClienteButtonActionPerformed(ActionEvent e) {
        ClienteForm clienteForm = new ClienteForm();
        clienteForm.setVisible(true);
    }

    private void registrarVentaButtonActionPerformed(ActionEvent e) {
        VentaForm ventaForm = new VentaForm();
        ventaForm.setVisible(true);
    }

    private void buscarMedicamentoButtonActionPerformed(ActionEvent e) {
        BuscarMedicamentoForm buscarForm = new BuscarMedicamentoForm();
        buscarForm.setVisible(true);
    }

    private void salirButtonActionPerformed(ActionEvent e) {
        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea cerrar sesión?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );
        if (opcion == JOptionPane.YES_OPTION) {
            LoginForm login = new LoginForm();
            login.setVisible(true);
            dispose();
        }
    }
}
