package sk.stuba.fei.uim.oop.shapes;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class Turtle extends JPanel {
    @Getter
    private int rotate;

    public Turtle() {
        this.rotate = 0;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(  Math.toRadians(rotate), this.getWidth() * 0.5, this.getHeight() * 0.5);
        g2d.setColor(Color.BLACK);
        g2d.fillOval(this.getWidth()/10,this.getHeight()/10,this.getWidth()/5*4, this.getHeight()/5*4 );
        g2d.setColor(Color.MAGENTA);
        g2d.fillOval(this.getWidth()/3,0,this.getWidth()/3,this.getHeight()/3);
        g2d.dispose();
    }

    public void rotate(int angle) {
        this.rotate += angle;
    }
}
