package Pro;


import java.util.*;

public class Unidad {
    private int numero;
    private String matricula;
    private Lugar lugarActual;
    private Horario horario;
    private List<Pasajero> pasajeros;

    // Constructor
    public Unidad(int numero, String matricula) {
        this.numero = numero;
        this.matricula = matricula;
        this.pasajeros = new ArrayList<>();
    }

    public void asignarLugar(Lugar lugar) {
        this.lugarActual = lugar;
    }

    public void asignarHorario(Horario horario) {
        this.horario = horario;
    }

    public void agregarPasajero(Pasajero pasajero) {
        this.pasajeros.add(pasajero);
    }

    public void mostrarInfo() {
        System.out.println("\nUnidad #" + numero + " - Matrícula: " + matricula);
        System.out.println("Lugar actual: " + lugarActual.getNombre());
        System.out.println("Horario:");
        System.out.println("  " + horario);
        System.out.println("Pasajeros:");
        for (Pasajero p : pasajeros) {
            System.out.println("  - " + p.getNombre());
        }
    }
}
