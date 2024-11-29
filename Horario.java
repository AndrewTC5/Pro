package Pro;

public class Horario {
    private String horaEntrada;
    private String horaSalida;

    // Constructor
    public Horario(String horaEntrada, String horaSalida) {
        this.setHoraEntrada(horaEntrada);
        this.setHoraSalida(horaSalida);
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    @Override
    public String toString() {
        return "Entrada: " + getHoraEntrada() + " - Salida: " + getHoraSalida();
    }
}
