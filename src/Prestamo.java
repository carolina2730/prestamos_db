public class Prestamo {
    private int id;
    private String nombre;
    private double monto;
    private int plazoMeses;      // int
    private double tasaInteres;  // double, no int
    private String estado;

    // Constructor vacío
    public Prestamo() {}

    // Constructor con todos los campos - OJO el orden
    public Prestamo(int id, String nombre, double monto, int plazoMeses, double tasaInteres, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.monto = monto;
        this.plazoMeses = plazoMeses;
        this.tasaInteres = tasaInteres;
        this.estado = estado;
    }

    // GETTERS - Sin parámetros, solo return
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getMonto() { return monto; }
    public int getPlazoMeses() { return plazoMeses; } // <-- Aquí estaba el error
    public double getTasaInteres() { return tasaInteres; } // <-- Y aquí. Es double, no int
    public String getEstado() { return estado; }

    // SETTERS
    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setMonto(double monto) { this.monto = monto; }
    public void setPlazoMeses(int plazoMeses) { this.plazoMeses = plazoMeses; }
    public void setTasaInteres(double tasaInteres) { this.tasaInteres = tasaInteres; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Prestamo{id=" + id + ", nombre='" + nombre + "', monto=" + monto + 
    ", plazo=" + plazoMeses + ", tasa=" + tasaInteres + ", estado='" + estado + "'}";
    }
}