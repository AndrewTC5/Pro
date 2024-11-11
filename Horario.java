package Pro;

import java.util.LinkedList;
import java.util.Queue;

public class Horario {
    private String horaEntrada;
    private String horaSalida;

    // Constructor
    public Horario(String horaEntrada, String horaSalida) {
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    @Override
    public String toString() {
        return "Entrada: " + horaEntrada + " - Salida: " + horaSalida;
    }
}
