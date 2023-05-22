package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.controls.GameLogic;

import javax.swing.*;
import java.awt.*;

public class SideMenu extends JPanel {
    public SideMenu(GameLogic logic) {
        this.setLayout(new GridLayout(2,3));
        JButton actionButton = createButton(GameLogic.BUTTON_ACTION_TEXT,logic);
        JButton colorButton = createButton(GameLogic.BUTTON_COLOR_TEXT,logic);

        this.add(logic.getTurtleAngleBox());
        this.add(logic.getTurtleStepSlider());
        this.add(actionButton);
        this.add(logic.getLabelStepTurtle());
        this.add(colorButton);
        this.add(logic.getLabelColorTurtle());
    }

    private JButton createButton(String text, GameLogic logic) {
        JButton button = new JButton(text);
        button.addActionListener(logic);
        button.setFocusable(false);
        return button;
    }
}
