package Pro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Queue;

public class MapaDialog extends JDialog {
    private MapaPanel mapaPanel;
    private JLabel lUnidadInfo;

    public MapaDialog(Queue<Unidad> unidades) {
        setTitle("Mapa de Recorrido");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setModal(true);

        mapaPanel = new MapaPanel(unidades);
        lUnidadInfo = new JLabel("Seleccione una parada para ver la información de la unidad.");
        lUnidadInfo.setHorizontalAlignment(SwingConstants.CENTER);
        lUnidadInfo.setFont(new Font("Arial", Font.BOLD, 14));

        add(mapaPanel, BorderLayout.CENTER);
        add(lUnidadInfo, BorderLayout.SOUTH);
    }

    public void actualizarInformacion(String info) {
        lUnidadInfo.setText(info);
    }

    private class MapaPanel extends JPanel {
        private Queue<Unidad> unidades;

        // Posiciones de las paradas en el mapa
        private Point[] posiciones = {
                new Point(50, 50),   // Parada 1
                new Point(150, 80),  // Parada 2
                new Point(250, 50),  // Parada 3
                new Point(350, 100), // Parada 4
                new Point(450, 50),  // Parada 5
                new Point(500, 150), // Parada 6
                new Point(400, 200), // Parada 7
                new Point(300, 150), // Parada 8
                new Point(200, 200), // Parada 9
                new Point(100, 250)  // Parada 10
        };

        public MapaPanel(Queue<Unidad> unidades) {
            this.unidades = unidades;
            setPreferredSize(new Dimension(600, 300));

            // Agregar MouseListener para detectar clics
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Point clickPoint = e.getPoint();
                    for (int i = 0; i < posiciones.length; i++) {
                        if (clickEnParada(clickPoint, posiciones[i])) {
                            mostrarInformacionUnidad(i);
                            break;
                        }
                    }
                }
            });
        }

        private boolean clickEnParada(Point clickPoint, Point parada) {
            // Verificar si el clic está dentro del área de la parada
            return clickPoint.x >= parada.x && clickPoint.x <= parada.x + 15 &&
                    clickPoint.y >= parada.y && clickPoint.y <= parada.y + 15;
        }

        private void mostrarInformacionUnidad(int indiceParada) {
            // Verificar si hay una unidad en la parada seleccionada
            for (Unidad unidad : unidades) {
                if (unidad.getLugarActual() != null && unidad.getLugarActual().getNombre().equals("Parada " + (indiceParada + 1))) {
                    actualizarInformacion(unidad.getNumero() + ". " + unidad.getMatricula() + "---" + unidad.getHorario());
                    return;
                }
            }
            actualizarInformacion("No hay unidades en esta parada.");
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Dibujar el fondo del mapa
            g.setColor(new Color(220, 220, 220));
            g.fillRect(0, 0, getWidth(), getHeight());

            // Dibujar el recorrido
            g.setColor(Color.LIGHT_GRAY);
            for (int i = 0; i < posiciones.length - 1; i++) {
                g.drawLine(posiciones[i].x + 7, posiciones[i].y + 7, posiciones[i + 1].x + 7, posiciones[i + 1].y + 7);
            }

            // Dibujar las paradas
            for (int i = 0; i < posiciones.length; i++) {
                g.setColor(Color.BLUE);
                g.fillOval(posiciones[i].x, posiciones[i].y, 15, 15);
                g.setColor(Color.BLACK);
                g.drawString("Parada " + (i + 1), posiciones[i].x + 20, posiciones[i].y + 12);

                // Dibujar la unidad si está en esta parada
                for (Unidad unidad : unidades) {
                    if (unidad.getLugarActual() != null && unidad.getLugarActual().getNombre().equals("Parada " + (i + 1))) {
                        g.setColor(Color.RED);
                        g.fillRect(posiciones[i].x + 5, posiciones[i].y + 5, 10, 10);
                    }
                }
            }
        }
    }
}