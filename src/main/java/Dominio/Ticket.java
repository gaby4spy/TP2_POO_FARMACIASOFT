package Dominio;

public class Ticket {
    private float total;
    private int cantidad;
    private  String nombreCliente;
    private float precio;
    private int dni;
    private String nombreMedicamento;


    public Ticket() {
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    // Getters
    public float getTotal() {
        return total;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public float getPrecio() {
        return precio;
    }

    public int getDni() {
        return dni;
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "total=" + total +
                ", cantidad=" + cantidad +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", precio=" + precio +
                ", dni=" + dni +
                ", nombreMedicamento='" + nombreMedicamento + '\'' +
                '}';
    }
}
