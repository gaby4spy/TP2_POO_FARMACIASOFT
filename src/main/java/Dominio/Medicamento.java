package Dominio;


public class Medicamento {
    private int idMedicamento;
    private String nombre;
    private String descripcion;
    private float precio;
    private int stockTotal;


    // Constructor vacío
    public Medicamento() {}

    // Constructor con id
    public Medicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public Medicamento(String nombre){
        this.nombre=nombre;
    }



    //constructor especifico para que pueda mapear la funcionaldiad del lotemdiecmanetodao-buscarPorProveedor
    public Medicamento (int id , String nombre){
        this.idMedicamento=id;
        this.nombre=nombre;

    }

    // Constructor con los atributos principales (sin id)
    public Medicamento(String nombre, String descripcion, float precio, int stockTotal) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stockTotal = stockTotal;

    }

    // Constructor completo (con id)
    public Medicamento(int idMedicamento, String nombre, String descripcion, float precio, int stockTotal) {
        this(nombre, descripcion, precio, stockTotal);
        this.idMedicamento = idMedicamento;
    }

    // Getters y Setters
    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStockTotal() {
        return stockTotal;
    }

    public void setStockTotal(int stockTotal) {
        this.stockTotal = stockTotal;
    }

    @Override
    public String toString() {
        return "Medicamento{" +
                "idMedicamento=" + idMedicamento +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", stockTotal=" + stockTotal +
                '}';
    }
}
