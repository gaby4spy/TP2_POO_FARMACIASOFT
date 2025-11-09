package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EmpleadoPanel extends JFrame {
    private JPanel mainPanel;
    private JButton buscarMedicamentoButton;
    private JButton salirButton;
    private JLabel tituloLabel;

    public EmpleadoPanel() {
        $$$setupUI$$$();
        setContentPane(mainPanel);
        setTitle("FarmaciaSoft - Panel de Empleado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        buscarMedicamentoButton.addActionListener(this::buscarMedicamentoButtonActionPerformed);
        salirButton.addActionListener(this::salirButtonActionPerformed);
    }

    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        tituloLabel = new JLabel();
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        tituloLabel.setText("Panel de Empleado");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(tituloLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        mainPanel.add(new JPanel(), gbc);

        buscarMedicamentoButton = new JButton();
        buscarMedicamentoButton.setFont(new Font("Arial", Font.PLAIN, 14));
        buscarMedicamentoButton.setText("Buscar Medicamentos");
        buscarMedicamentoButton.setPreferredSize(new Dimension(200, 50));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        mainPanel.add(buscarMedicamentoButton, gbc);

        salirButton = new JButton();
        salirButton.setFont(new Font("Arial", Font.PLAIN, 14));
        salirButton.setText("Salir");
        salirButton.setPreferredSize(new Dimension(200, 50));
        gbc.gridy = 3;
        mainPanel.add(salirButton, gbc);
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
