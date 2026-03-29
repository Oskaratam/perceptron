import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Plotter extends JFrame {
    public Plotter(List<double[]> points, List<String> labels, double[] weights, double threshold) {
        setTitle("Perceptron Decision Boundary");
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                double scale = 80.0;
                int ox = 100, oy = 500;

                for (int i = 0; i < points.size(); i++) {
                    int px = ox + (int) (points.get(i)[1] * scale);
                    int py = oy - (int) (points.get(i)[2] * scale);
                    g2.setColor(labels.get(i).equalsIgnoreCase("setosa") ? Color.RED : Color.BLUE);
                    g2.fillOval(px - 4, py - 4, 8, 8);
                }

                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(2));

                double w0 = weights[1];
                double w1 = weights[2];
                double t = threshold;

                double xStart = 0, xEnd = 8;

                double yStart = (t - (w0 * xStart)) / w1;
                double yEnd = (t - (w0 * xEnd)) / w1;

                int x1 = ox + (int) (xStart * scale);
                int y1 = oy - (int) (yStart * scale);
                int x2 = ox + (int) (xEnd * scale);
                int y2 = oy - (int) (yEnd * scale);

                g2.drawLine(x1, y1, x2, y2);
            }
        });
        setVisible(true);
    }
}