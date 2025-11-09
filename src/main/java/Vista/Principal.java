package Vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal extends JFrame {
    private JPanel principal;
    private JButton administradorButton;
    // empleadoButton está definido en el .form pero no se usa actualmente
    @SuppressWarnings("unused")
    private JButton empleadoButton;

    public Principal() {
        inicializarForma();

        // 👉 Acción al hacer clic en el botón "Administrador"
        administradorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear la ventana de administrador
                Administrador admin = new Administrador();
                admin.setVisible(true);

                // Cerrar la ventana actual (opcional)
                dispose();
            }
        });
    }

    private void inicializarForma() {
        setContentPane(principal);
        setTitle("Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Principal p = new Principal();
            p.setVisible(true);
        });
    }
}
