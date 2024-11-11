package Pro;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Definir lugares como una pila (Stack) de paradas fijas
        Stack<Lugar> lugares = new Stack<>();
        for (int i = 1; i <= 10; i++) {
            lugares.push(new Lugar("Parada " + i));
        }

        // Definir horarios como una cola (Queue) de horarios preestablecidos
        Queue<Horario> horarios = new LinkedList<>();
        horarios.add(new Horario("08:00", "08:30"));
        horarios.add(new Horario("09:00", "09:30"));
        horarios.add(new Horario("10:00", "10:30"));
        horarios.add(new Horario("11:00", "11:30"));
        horarios.add(new Horario("12:00", "12:30"));

        // Crear una cola para las unidades (Queue) y una lista para los pasajeros
        Queue<Unidad> unidades = new LinkedList<>();
        List<Pasajero> pasajeros = new ArrayList<>();

        // Menú principal
        while (true) {
            System.out.println("\n** Menú Principal **");
            System.out.println("1. Crear Pasajero");
            System.out.println("2. Crear Unidad");
            System.out.println("3. Ver Unidades");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    crearPasajero(sc, pasajeros);
                    break;

                case 2:
                    crearUnidad(sc, lugares, horarios, unidades, pasajeros);
                    break;

                case 3:
                    verUnidades(unidades);
                    break;

                case 4:
                    // Salir
                    System.out.println("¡Hasta luego!");
                    sc.close();
                    return;

                default:
                    System.out.println("Opción no válida. Por favor, elige una opción correcta.");
            }
        }
    }

    // Método para crear un pasajero
    private static void crearPasajero(Scanner sc, List<Pasajero> pasajeros) {
        System.out.print("Introduce el nombre del pasajero: ");
        String nombrePasajero = sc.nextLine();
        Pasajero pasajero = new Pasajero(nombrePasajero);
        pasajeros.add(pasajero);
        System.out.println("Pasajero creado con éxito.");
    }

    // Método para crear una unidad
    private static void crearUnidad(Scanner sc, Stack<Lugar> lugares, Queue<Horario> horarios,
                                    Queue<Unidad> unidades, List<Pasajero> pasajeros) {
        System.out.print("Introduce el número de la unidad: ");
        int numeroUnidad = sc.nextInt();
        System.out.print("Introduce la matrícula de la unidad: ");
        sc.nextLine(); // Consumir el salto de línea
        String matriculaUnidad = sc.nextLine();
        Unidad unidad = new Unidad(numeroUnidad, matriculaUnidad);

        // Seleccionar el lugar para la unidad
        seleccionarLugar(sc, lugares, unidad);

        // Seleccionar el horario para la unidad
        seleccionarHorario(sc, horarios, unidad);

        // Asignar pasajeros a la unidad
        asignarPasajeros(sc, pasajeros, unidad);

        // Agregar la unidad a la cola
        unidades.add(unidad);
        System.out.println("Unidad creada con éxito.");
    }

    // Método para seleccionar un lugar para la unidad
    private static void seleccionarLugar(Scanner sc, Stack<Lugar> lugares, Unidad unidad) {
        System.out.println("Lugares disponibles:");
        int index = 0;
        for (Lugar lugar : lugares) {
            System.out.println(index++ + ". " + lugar.getNombre());
        }
        System.out.print("Selecciona el lugar de la unidad: ");
        int indiceLugar = sc.nextInt();
        if (indiceLugar >= 0 && indiceLugar < lugares.size()) {
            Lugar lugarSeleccionado = lugares.get(indiceLugar);
            unidad.asignarLugar(lugarSeleccionado);
            System.out.println("Lugar asignado.");
        } else {
            System.out.println("Índice de lugar no válido.");
        }
    }

    // Método para seleccionar un horario para la unidad
    private static void seleccionarHorario(Scanner sc, Queue<Horario> horarios, Unidad unidad) {
        System.out.println("Horarios disponibles:");
        int index = 0;
        for (Horario horario : horarios) {
            System.out.println(index++ + ". " + horario);
        }
        System.out.print("Selecciona el horario de la unidad: ");
        int indiceHorario = sc.nextInt();
        if (indiceHorario >= 0 && indiceHorario < horarios.size()) {
            // Usamos poll() para obtener y eliminar el primer elemento de la cola
            Horario horarioSeleccionado = horarios.poll();
            unidad.asignarHorario(horarioSeleccionado);
            System.out.println("Horario asignado.");
        } else {
            System.out.println("Índice de horario no válido.");
        }
    }

    // Método para asignar pasajeros a la unidad
    private static void asignarPasajeros(Scanner sc, List<Pasajero> pasajeros, Unidad unidad) {
        System.out.println("Asignar pasajeros a la unidad:");
        for (Pasajero p : pasajeros) {
            System.out.println(p.getNombre());
        }
        System.out.print("¿Deseas asignar pasajeros a la unidad? (s/n): ");
        sc.nextLine(); // Consumir salto de línea
        String respuesta = sc.nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            while (true) {
                System.out.print("Introduce el nombre del pasajero a asignar (o 'fin' para terminar): ");
                String nombrePasajeroAsignado = sc.nextLine();
                if (nombrePasajeroAsignado.equalsIgnoreCase("fin")) {
                    break;
                }
                // Buscar pasajero
                boolean encontrado = false;
                for (Pasajero pas : pasajeros) {
                    if (pas.getNombre().equals(nombrePasajeroAsignado)) {
                        unidad.agregarPasajero(pas);
                        encontrado = true;
                        System.out.println("Pasajero asignado.");
                        break;
                    }
                }
                if (!encontrado) {
                    System.out.println("Pasajero no encontrado.");
                }
            }
        }
    }

    // Método para ver todas las unidades
    private static void verUnidades(Queue<Unidad> unidades) {
        if (unidades.isEmpty()) {
            System.out.println("No hay unidades creadas.");
        } else {
            for (Unidad u : unidades) {
                u.mostrarInfo();
            }
        }
    }
}

