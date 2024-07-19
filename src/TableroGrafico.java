import javax.swing.*;
import java.awt.*;
/*
 Proyecto - Simulación de una isla, automáta celular
 Clase TableroGrafico - Crea un tablero de forma gráfica
 Autor - Mario García. mariogarcia1040@gmail.com
 29-Junio-2024 | 1-Julio-2024
*/
public class TableroGrafico extends JFrame {
    public TableroGrafico() {
        // Datos de la ventana
        setTitle("SeresVivos.Isla Simulación v. 0.0.0");
        setSize(560, 600);
        setResizable(false);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Malla de 16x16
    public void paint(Graphics g) {
        super.paint(g);
        for (int x = 40; x <= 500; x += 30)
            for (int y = 40; y <= 500; y += 30)
                g.drawRect(x, y, 30, 30);
        g.drawString("\uD83D\uDC3B", 50, 52);
        g.drawString("200", 45, 65);
    }
}
