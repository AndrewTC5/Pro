package Pro;

public class Pasajero {
    private String nombre;

    // Constructor
    public Pasajero(String nombre) {
        this.setNombre(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}