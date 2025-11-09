package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Administrador extends JFrame {
    private JPanel mainPanel;
    private JButton medicamentosButton;
    private JButton salirButton;
    private JLabel tituloLabel;

    public Administrador() {
        $$$setupUI$$$();
        setContentPane(mainPanel);
        setTitle("FarmaciaSoft - Panel de Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        medicamentosButton.addActionListener(this::medicamentosButtonActionPerformed);
        salirButton.addActionListener(this::salirButtonActionPerformed);
    }

    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        tituloLabel = new JLabel();
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        tituloLabel.setText("Panel de Administrador");
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

        medicamentosButton = new JButton();
        medicamentosButton.setFont(new Font("Arial", Font.PLAIN, 14));
        medicamentosButton.setText("Gestionar Medicamentos");
        medicamentosButton.setPreferredSize(new Dimension(250, 50));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        mainPanel.add(medicamentosButton, gbc);

        salirButton = new JButton();
        salirButton.setFont(new Font("Arial", Font.PLAIN, 14));
        salirButton.setText("Salir");
        salirButton.setPreferredSize(new Dimension(250, 50));
        gbc.gridy = 3;
        mainPanel.add(salirButton, gbc);
    }

    private void medicamentosButtonActionPerformed(ActionEvent e) {
        MedicamentoForm medicamentoForm = new MedicamentoForm();
        medicamentoForm.setVisible(true);
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
