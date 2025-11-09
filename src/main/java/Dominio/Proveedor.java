package Dominio;

public class Proveedor {

    private String id;
    private String razonSocial;
    private String dni;
    private String telefono;
    private char estado; // 'A' = Activo, 'I' = Inactivo

    public Proveedor() {}

    public Proveedor(String id) {
        this.id = id;
    }

    public Proveedor(String razonSocial, String dni, String telefono) {
        this.razonSocial = razonSocial;
        this.dni = dni;
        this.telefono = telefono;
        this.estado = 'A'; // por defecto al crear un proveedor nuevo
    }

    public Proveedor(String id, String razonSocial, String dni, String telefono) {
        this(razonSocial, dni, telefono);
        this.id = id;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getRazonSocial() { return razonSocial; }
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public char getEstado() { return estado; }
    public void setEstado(char estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Proveedor{" +
                "id='" + id + '\'' +
                ", razonSocial='" + razonSocial + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                ", estado=" + estado +
                '}';
    }
}
