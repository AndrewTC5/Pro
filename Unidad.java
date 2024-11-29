package Pro;

import java.util.*;

public class Unidad {
    private int numero;
    private String matricula;
    private Lugar lugarActual;
    private Horario horario;
    private List<String> pasajeros;

    // Constructor
    public Unidad(int numero, String matricula) {
        this.setNumero(numero);
        this.setMatricula(matricula);
        this.setPasajeros(new ArrayList<>());
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Lugar getLugarActual() {
        return lugarActual;
    }

    public void setLugarActual(Lugar lugarActual) {
        this.lugarActual = lugarActual;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public List<String> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(List<String> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public void asignarLugar(Lugar lugar) {
        this.setLugarActual(lugar);
    }

    public void asignarHorario(Horario horario) {
        this.setHorario(horario);
    }

    public void agregarPasajero(String pasajero) {
        this.getPasajeros().add(pasajero);
    }

    public Object mostrarInfo() {
        int sumaPasajes = 8* getPasajeros().size();
        return "Unidad #" + getNumero() + " - Matricula: " + getMatricula()
                + "\nLugar Actual: " + getLugarActual().getNombre() +
                "\nHorario: " + getHorario() +
                "\nNumero de Pasajeros en " + getLugarActual().getNombre() + ": "+ getPasajeros().size() +
                "\nPasaje total: " + sumaPasajes;
    }
    public String mostrarInfoLabel() {
        int sumaPasajes = 8* getPasajeros().size();
        return "Unidad #" + getNumero() + " - Matricula: " + getMatricula()
                + "\nLugar Actual: " + getLugarActual().getNombre() +
                "\nHorario: " + getHorario() +
                "\nNumero de Pasajeros en " + getLugarActual().getNombre() + ": "+ getPasajeros().size() +
                "\nPasaje total: " + sumaPasajes;
    }
}
