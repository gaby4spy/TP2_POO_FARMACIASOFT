package Dominio;

import java.time.LocalDateTime;

public class DetalleVenta {
    private int idDetalle;
    private int idVenta;
    private int idLote;
    private int cantidad;
    private double precioUnitario;
    private double total;
    private LocalDateTime fecha;

    public DetalleVenta() {}

    public DetalleVenta(int idVenta, int idLote, int cantidad, double precioUnitario, double total, LocalDateTime fecha) {
        this.idVenta = idVenta;
        this.idLote = idLote;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.total = total;
        this.fecha = fecha;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "DetalleVenta{" +
                "idDetalle=" + idDetalle +
                ", idVenta=" + idVenta +
                ", idLote=" + idLote +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", total=" + total +
                ", fecha=" + fecha +
                '}';
    }
}
