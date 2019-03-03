public class Carta {

    private String nombre;
    private String tipo;
    private int cantidad = 1;

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Carta(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }
}
