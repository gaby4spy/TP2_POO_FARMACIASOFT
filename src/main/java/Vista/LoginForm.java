package Vista;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoginForm extends JFrame {
    private JPanel mainPanel;
    private JTextField usuarioTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JComboBox<String> tipoUsuarioCombo;
    private JLabel tituloLabel;
    private JLabel usuarioLabel;
    private JLabel passwordLabel;
    private JLabel tipoLabel;

    public LoginForm() {
        setContentPane(mainPanel);
        setTitle("FarmaciaSoft - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);
        setResizable(false);

        loginButton.addActionListener(this::loginButtonActionPerformed);
        passwordField.addActionListener(e -> loginButton.doClick());
    }

    private void loginButtonActionPerformed(ActionEvent e) {
        String usuario = usuarioTextField.getText().trim();
        String password = new String(passwordField.getPassword());
        String tipoUsuario = (String) tipoUsuarioCombo.getSelectedItem();

        if (usuario.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, complete todos los campos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (tipoUsuario.equals("Administrador")) {
            Administrador adminPanel = new Administrador();
            adminPanel.setVisible(true);
            dispose();
        } else if (tipoUsuario.equals("Empleado")) {
            EmpleadoPanel empleadoPanel = new EmpleadoPanel();
            empleadoPanel.setVisible(true);
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginForm login = new LoginForm();
            login.setVisible(true);
        });
    }
}
