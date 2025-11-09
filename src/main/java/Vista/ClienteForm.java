package Vista;

import Controlador.EmpleadoController;
import Datos.*;
import Dominio.Cliente;
import Servicio.*;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ClienteForm extends JFrame {
    private JPanel mainPanel;
    private JTextField nombreTextField;
    private JTextField dniTextField;
    private JTextField telefonoTextField;
    private JButton registrarButton;
    private JButton limpiarButton;
    private JLabel tituloLabel;
    private EmpleadoController controller;

    public ClienteForm() {
        setContentPane(mainPanel);
        setTitle("Registrar Cliente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        inicializarController();
        registrarButton.addActionListener(this::registrarButtonActionPerformed);
        limpiarButton.addActionListener(this::limpiarButtonActionPerformed);
    }

    private void inicializarController() {
        UsuarioService usuarioService = new UsuarioService(new ClienteDAO());
        EmpleadoService empleadoService = new EmpleadoService(
                usuarioService,
                new DetalleVentaService(new DetalleVentaDAO()),
                new MedicamentoService(new MedicamentoDAO()),
                new VentaService(new VentaDAO()),
                new TicketService(new TicketDAO())
        );
        controller = new EmpleadoController(empleadoService);
    }

    private void registrarButtonActionPerformed(ActionEvent e) {
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
        boolean exito = controller.agregarCliente(cliente);

        if (exito) {
            JOptionPane.showMessageDialog(this,
                    "Cliente registrado correctamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Error al registrar el cliente",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarButtonActionPerformed(ActionEvent e) {
        limpiarCampos();
    }

    private void limpiarCampos() {
        nombreTextField.setText("");
        dniTextField.setText("");
        telefonoTextField.setText("");
    }
}
