package Pro;

import java.util.*;

public class Lugar {
    private String nombre;

    // Constructor
    public Lugar(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }


}
