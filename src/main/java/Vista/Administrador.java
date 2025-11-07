package Vista;

import javax.swing.*;

public class Administrador extends JFrame {
    private JPanel panel1;
    private JButton medicamentosButton;
    private JButton proveedoresButton;
    private JButton stockButton;
    private JButton reportesButton;
    private JButton usuariosButton;

    public Administrador() {
        inicializarForma();
    }

    private void inicializarForma() {
        setContentPane(panel1);               // Usa el panel diseñado
        setTitle("Panel de Administrador");   // Título de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);                    // Tamaño de la ventana
        setLocationRelativeTo(null);          // Centrar en pantalla
    }

    public static void main(String[] args) {
        // Crear y mostrar la ventana de administrador
        SwingUtilities.invokeLater(() -> {
            Administrador admin = new Administrador();
            admin.setVisible(true);
        });
    }
}
