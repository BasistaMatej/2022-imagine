package sk.stuba.fei.uim.oop.controls;

import lombok.Getter;
import sk.stuba.fei.uim.oop.gui.Imagine;
import sk.stuba.fei.uim.oop.shapes.Line;
import sk.stuba.fei.uim.oop.shapes.Turtle;
import sk.stuba.fei.uim.oop.utility.Colors;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class GameLogic extends UniversalAdapter {
    @Getter
    private JLabel labelStepTurtle;
    @Getter
    private JLabel labelColorTurtle;
    @Getter
    private JPanel mainPanel;
    @Getter
    private JComboBox turtleAngleBox;
    @Getter
    private JSlider turtleStepSlider;

    private Turtle turtle;

    private Colors activeColor;
    private int currentAngle;

    public static final String BUTTON_ACTION_TEXT = "Akcia";
    public static final String BUTTON_COLOR_TEXT = "Farba";
    private static final int TURTLE_SIZE = 100;
    private int[] coords;

    public GameLogic() {
        this.activeColor = Colors.GREEN;
        this.labelStepTurtle = new JLabel();
        this.labelColorTurtle = new JLabel();

        createTurtleStepSlider();
        createTurtleAngleBox();

        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(null);

        this.coords = new int[2];
        this.coords[0] = Imagine.FRAME_SIZE/2-50;
        this.coords[1] = Imagine.FRAME_SIZE/2-100;

        this.turtle = new Turtle();
        this.turtle.setBounds(Imagine.FRAME_SIZE/2-50,Imagine.FRAME_SIZE/2-100,TURTLE_SIZE,TURTLE_SIZE);
        this.mainPanel.add(this.turtle);

        updateLabelStepTurtle();
        updateLabelColorTurtle();

    }

    private void createTurtleStepSlider() {
        this.turtleStepSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
        this.turtleStepSlider.addChangeListener(this);
        this.turtleStepSlider.setMinorTickSpacing(10);
        this.turtleStepSlider.setMajorTickSpacing(10);
        this.turtleStepSlider.setSnapToTicks(true);
        this.turtleStepSlider.setPaintTicks(true);

        this.turtleStepSlider.setFocusable(false);
    }

    private void createTurtleAngleBox() {
        String[] options = {"0", "5", "10", "45", "90", "180"};
        this.turtleAngleBox = new JComboBox(options);
        this.turtleAngleBox.addActionListener(this);
        this.turtleAngleBox.setSelectedItem(options[3]);
        this.turtleAngleBox.setFocusable(false);
    }

    private void updateLabelStepTurtle() {
        this.labelStepTurtle.setText("Krok: "+this.turtleStepSlider.getValue()+" | "+this.turtleAngleBox.getSelectedItem()+"°");
    }

    private void updateLabelColorTurtle() {
        this.labelColorTurtle.setText("Farba: "+this.activeColor.getTranslateName());
    }

    private void changeColor() {
        if(this.activeColor.getIndex() > 2) {
            this.activeColor = Colors.GREEN;
        } else {
            this.activeColor = Arrays.stream(Colors.values()).filter(color -> (this.activeColor.getIndex()+1) == color.getIndex()).findFirst().get();
        }
    }

    private void moveTurtle(int direction) {
        int step = this.turtleStepSlider.getValue();
        Line newLine = new Line(turtle.getRotate(), this.activeColor.getColor());
        newLine.setBounds(this.coords[0]+TURTLE_SIZE/2,this.coords[1]+TURTLE_SIZE/2,step,step);
        this.mainPanel.add(newLine);
        this.coords[0] = (int) (this.coords[0]+(direction)*step*Math.sin(Math.toRadians(turtle.getRotate())));
        this.coords[1] = (int) (this.coords[1]-(direction)*step*Math.cos(Math.toRadians(turtle.getRotate())));
        this.turtle.setBounds(this.coords[0],this.coords[1],TURTLE_SIZE,TURTLE_SIZE);
        this.mainPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton) {
            JButton jButton = (JButton) e.getSource();
            switch (jButton.getText()) {
                case BUTTON_ACTION_TEXT:
                    this.turtle.rotate(Integer.parseInt((String)this.turtleAngleBox.getSelectedItem()));
                    moveTurtle(1);
                    break;
                case BUTTON_COLOR_TEXT:
                    changeColor();
                    updateLabelColorTurtle();
                    break;
            }

        } else if(e.getSource() instanceof JComboBox) {
            updateLabelStepTurtle();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() instanceof JSlider) {
            updateLabelStepTurtle();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int angle = Integer.parseInt((String)this.turtleAngleBox.getSelectedItem());
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                this.turtle.rotate(angle);
                this.mainPanel.repaint();
                break;
            case KeyEvent.VK_LEFT:
                this.turtle.rotate((-1)*angle);
                this.mainPanel.repaint();
                break;
            case KeyEvent.VK_DOWN:
                moveTurtle(-1);
                break;
            case KeyEvent.VK_UP:
                moveTurtle(1);
                break;
        }
    }
}
