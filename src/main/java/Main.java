import Controlador.AdministradorController;
import Datos.*;
import Dominio.*;
import Servicio.AdministradorService;
import Servicio.MedicamentoService;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {





        IMedicamentoDAO medDao = new MedicamentoDAO();
        MedicamentoService medService = new MedicamentoService(medDao);
        AdministradorService adminService = new AdministradorService(medService);
        AdministradorController adminController = new AdministradorController(adminService);

// Simulamos un administrador logueado
        Administrador admin = new Administrador();

// Buscar por nombre










    }
}
