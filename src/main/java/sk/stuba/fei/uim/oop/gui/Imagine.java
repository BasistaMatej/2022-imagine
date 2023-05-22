package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.controls.GameLogic;

import javax.swing.*;
import java.awt.*;

public class Imagine {
    public static final int FRAME_SIZE = 700;
    public Imagine() {
        JFrame frame = new JFrame("IMAGINE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_SIZE, FRAME_SIZE);
        frame.setLayout(new BorderLayout());

        GameLogic logic = new GameLogic();
        frame.add(new SideMenu(logic),BorderLayout.PAGE_END);
        frame.add(logic.getMainPanel(),BorderLayout.CENTER);
        frame.addKeyListener(logic);

        frame.setFocusable(true);
        frame.setVisible(true);
    }
}
