package sk.stuba.fei.uim.oop.shapes;

import sk.stuba.fei.uim.oop.utility.Colors;

import javax.swing.*;
import java.awt.*;

public class Line extends JPanel {
    private int rotate;

    private Color color;

    public Line(int rotate, Color color) {
        this.rotate = rotate;
        this.color = color;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.rotate(  Math.toRadians(rotate), this.getWidth() * 0.5, this.getHeight() * 0.5);
        g2d.drawLine(this.getWidth()/2,0,this.getWidth()/2,this.getHeight());
        g2d.dispose();
    }
}
