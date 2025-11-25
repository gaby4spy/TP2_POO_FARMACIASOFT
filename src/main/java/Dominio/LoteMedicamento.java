package Dominio;

import java.time.LocalDate;

public class LoteMedicamento {
    private int idCompra;
    private LocalDate fechaCompra;
    private String lote;
    private LocalDate fechaVencimiento;
    private int cantidadAdquirida;
    private String laboratorio;
    private Medicamento medicamento;   // Relación con entidad Medicamento
    private Proveedor proveedor;       // Relación con entidad Proveedor

    // Constructor vacío (para mapeo desde la BD)
    public LoteMedicamento() {}

    // Constructor con todos los campos (útil para crear un lote completo)
    public LoteMedicamento(int idCompra, LocalDate fechaCompra, String lote, LocalDate fechaVencimiento,
                           int cantidadAdquirida, String laboratorio,
                           Medicamento medicamento, Proveedor proveedor) {
        this.idCompra = idCompra;
        this.fechaCompra = fechaCompra;
        this.lote = lote;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidadAdquirida = cantidadAdquirida;
        this.laboratorio = laboratorio;
        this.medicamento = medicamento;
        this.proveedor = proveedor;
    }

    // Constructor sin ID (para cuando se va a insertar en la BD y el ID es autogenerado)
    public LoteMedicamento(LocalDate fechaCompra, String lote, LocalDate fechaVencimiento,
                           int cantidadAdquirida, String laboratorio,
                           Medicamento medicamento, Proveedor proveedor) {
        this.fechaCompra = fechaCompra;
        this.lote = lote;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidadAdquirida = cantidadAdquirida;
        this.laboratorio = laboratorio;
        this.medicamento = medicamento;
        this.proveedor = proveedor;
    }

    // Getters y Setters
    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getCantidadAdquirida() {
        return cantidadAdquirida;
    }

    public void setCantidadAdquirida(int cantidadAdquirida) {
        this.cantidadAdquirida = cantidadAdquirida;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public String toString() {
        return "LoteMedicamento{" +
                "idCompra=" + idCompra +
                ", fechaCompra=" + fechaCompra +
                ", lote='" + lote + '\'' +
                ", fechaVencimiento=" + fechaVencimiento +
                ", cantidadAdquirida=" + cantidadAdquirida +
                ", laboratorio='" + laboratorio + '\'' +
                ", medicamento=" + medicamento +
                ", proveedor=" + proveedor +
                '}';
    }
}
