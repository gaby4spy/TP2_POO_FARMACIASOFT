package Dominio;

public class Venta {

    private int idVenta;
    private int idEmpleado;
    private int idCliente;
    private float total;




    public Venta() {}


    public Venta(int idVenta) {
        this.idVenta = idVenta;
    }


    public Venta(int idEmpleado, int idCliente, float total) {
        this.idEmpleado = idEmpleado;
        this.idCliente = idCliente;
        this.total = total;
    }


    public Venta(int idVenta, int idEmpleado, int idCliente, float total) {
        this(idEmpleado, idCliente, total);
        this.idVenta = idVenta;
    }

    // Getters y Setters
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }


    @Override
    public String toString() {
        return "Venta{" +
                "idVenta=" + idVenta +
                ", idEmpleado=" + idEmpleado +
                ", idCliente=" + idCliente +
                ", total=" + total +
                '}';
    }
}
