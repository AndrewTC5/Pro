package Pro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class vistaRecorrido extends JFrame {
    private JPanel panelPrincipal;
    private JTextField tfNumUnidad;
    private JTextField tfMatricula;
    private JTextArea taLista;
    private JButton bSimular;
    private JButton bVerMapa;
    private JButton verListaDeUnidadesButton;

    public static void main(String[] args) {
        vistaRecorrido fr = new vistaRecorrido();
        fr.setBounds(100, 100, 650, 500);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setTitle("Recorrido de Transportes");
        fr.setResizable(false);
    }


    public vistaRecorrido() {
        Stack<Lugar>lugares = new Stack<>();
        for (int i =1; i<=10; i++){
            lugares.push(new Lugar("Parada " + i));
        }

        Queue<Horario> horarios = new LinkedList<>();
        horarios.add(new Horario("08:00", "08:30"));
        horarios.add(new Horario("09:00", "09:30"));
        horarios.add(new Horario("10:00", "10:30"));
        horarios.add(new Horario("11:00", "11:30"));
        horarios.add(new Horario("12:00", "12:30"));

        // Crear una cola para las unidades (Queue) y una lista para los pasajeros
        Queue<Unidad> unidades = new LinkedList<>();
        List<Pasajero> pasajeros = new ArrayList<>();

        add(panelPrincipal);


        verListaDeUnidadesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (unidades.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Unidad no encontrado");
                } else {
                    for (Unidad unidad : unidades) {
                        JOptionPane.showMessageDialog(null, unidad.mostrarInfo());
                    }
                }
            }
        });

        bSimular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                byte numUnidad = Byte.parseByte(tfNumUnidad.getText());
                String matricula = tfMatricula.getText();
                Unidad unidad = new Unidad(numUnidad, matricula);
                unidades.add(unidad);
                taLista.append("Unidad " + numUnidad + " " + matricula + "\n");

                seleccionarLugar(lugares, unidad);
                seleccionarHorario(horarios, unidad);
                agregarPasajeros(pasajeros, unidad);
            }
        });


        bVerMapa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapaDialog mapaDialog = new MapaDialog(unidades);
                mapaDialog.setVisible(true);
            }
        });
    }

    private static void seleccionarLugar(Stack<Lugar> lugares, Unidad unidad) {
        int index = 1;
        StringBuilder lugaresC = new StringBuilder("Lugares Disponibles: \n");

        for (Lugar lugar : lugares){
            lugaresC.append(index).append(": ").append(lugar.toString()).append("\n");
            index++;
        }
        JOptionPane.showMessageDialog(null, lugaresC.toString());
        int indiceLugar;


        indiceLugar = Byte.parseByte(JOptionPane.showInputDialog(null, "Ingrese el lugar de la Unidad"));
        if (indiceLugar >= 0 && indiceLugar < lugares.size()) {
            Lugar lugarSeleccionado = lugares.get(indiceLugar-1);
            unidad.asignarLugar(lugarSeleccionado);
            JOptionPane.showMessageDialog(null,"Lugar asignado.");
        } else {
            JOptionPane.showMessageDialog(null,"Índice de lugar no válido.");
        }
    }

    private static void seleccionarHorario(Queue<Horario> horarios, Unidad unidad) {
        StringBuilder horariosString = new StringBuilder("Horarios disponibles:\n");
        int index = 1; // Inicializar el índice
        for (Horario horario : horarios) {
            horariosString.append(index).append(": ").append(horario.toString()).append("\n");
            index++; // Incrementar el índice
        }

        JOptionPane.showMessageDialog(null, horariosString.toString(), "Lista de Horarios", JOptionPane.INFORMATION_MESSAGE);

            // Usamos poll() para obtener y eliminar el primer elemento de la cola
            Horario horarioSeleccionado = horarios.poll();
            unidad.asignarHorario(horarioSeleccionado);
            JOptionPane.showMessageDialog(null, "Horario asignado: " + horarioSeleccionado);
    }

    private static void agregarPasajeros(List<Pasajero> pasajeros, Unidad unidad) {
        String respuesta;
        respuesta=JOptionPane.showInputDialog(null, "¿Desea agregar pasajeros?");
        if (respuesta.equalsIgnoreCase("Si")){
            while (true){
                String persona= JOptionPane.showInputDialog("Introduce el nombre del pasajero\n(Si desea Finalizar la Tarea escriba 'TERMINAR')");
                pasajeros.add(new Pasajero(persona));
                String listaPersonas= String.valueOf(pasajeros.add(new Pasajero(persona)));
                unidad.agregarPasajero(listaPersonas);
                if (persona.equalsIgnoreCase("TERMINAR")){
                    break;
                }
            }
        }
    }
}