package Vista;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Administrador extends JFrame {
    private JPanel mainPanel;
    private JButton medicamentosButton;
    private JButton proveedoresButton;
    private JButton reportesButton;
    private JButton usuariosButton;
    private JButton salirButton;
    private JLabel tituloLabel;

    public Administrador() {
        setContentPane(mainPanel);
        setTitle("FarmaciaSoft - Panel de Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        medicamentosButton.addActionListener(this::medicamentosButtonActionPerformed);
        proveedoresButton.addActionListener(this::proveedoresButtonActionPerformed);
        usuariosButton.addActionListener(this::usuariosButtonActionPerformed);
        reportesButton.addActionListener(this::reportesButtonActionPerformed);
        salirButton.addActionListener(this::salirButtonActionPerformed);
    }

    private void medicamentosButtonActionPerformed(ActionEvent e) {
        MedicamentoForm medicamentoForm = new MedicamentoForm();
        medicamentoForm.setVisible(true);
    }

    private void proveedoresButtonActionPerformed(ActionEvent e) {
        ProveedorForm proveedorForm = new ProveedorForm();
        proveedorForm.setVisible(true);
    }

    private void usuariosButtonActionPerformed(ActionEvent e) {
        UsuarioForm usuarioForm = new UsuarioForm();
        usuarioForm.setVisible(true);
    }

    private void reportesButtonActionPerformed(ActionEvent e) {
        ReportesForm reportesForm = new ReportesForm();
        reportesForm.setVisible(true);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Administrador admin = new Administrador();
            admin.setVisible(true);
        });
    }
}
