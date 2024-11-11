package Pro;

import java.util.ArrayList;
import java.util.List;

public class Pasajes {
    private List<String> nombres;

    public Pasajes() {
        this.nombres = new ArrayList<>();
    }

    public void ingresarPasaje(int i) {
        if (i != 0) {
            nombres.add(String.valueOf(i));
        }
    }

    public List<String> obtenerPasajeros() {
        return nombres;
    }
}