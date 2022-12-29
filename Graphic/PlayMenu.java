package Graphic;

import Common.Display;
import Common.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayMenu extends JPanel implements ActionListener {
    JButton buttonDomino, buttonCarcassonne, buttonReturn;

    public PlayMenu(int width, int height) {
        this.setSize(width, height);
        this.setLayout(null);

        Dimension buttonDimension = new Dimension(Display.BUTTON_WIDTH.getValue(), Display.BUTTON_HEIGHT.getValue());

        buttonDomino = new JButton();
        this.add(buttonDomino);
        buttonDomino.setText("Domino");
        buttonDomino.setSize(buttonDimension);
        buttonDomino.setLocation(Display.CENTER_X.getValue() - (Display.BUTTON_WIDTH.getValue() + Display.DISTANCE_BETWEEN_BUTTONS.getValue() / 2), Display.CENTER_Y.getValue() - Display.PADDING_Y.getValue());
        buttonDomino.addActionListener(this);

        buttonCarcassonne = new JButton();
        this.add(buttonCarcassonne);
        buttonCarcassonne.setText("Carcassonne");
        buttonCarcassonne.setSize(buttonDimension);
        buttonCarcassonne.setLocation(Display.CENTER_X.getValue() + Display.DISTANCE_BETWEEN_BUTTONS.getValue() / 2, Display.CENTER_Y.getValue() - Display.PADDING_Y.getValue());
        buttonCarcassonne.addActionListener(this);

        buttonReturn = new JButton();
        this.add(buttonReturn);
        buttonReturn.setText("Return");
        buttonReturn.setSize(buttonDimension);
        buttonReturn.setLocation(Display.WIDTH.getValue() - (Display.BUTTON_WIDTH.getValue() + Display.DISTANCE_BETWEEN_BUTTONS.getValue()), Display.HEIGHT.getValue() - (Display.BUTTON_HEIGHT.getValue() + Display.DISTANCE_BETWEEN_BUTTONS.getValue() * 2));
        buttonReturn.addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String sIdentifier = "";
        JButton buttonSource = (JButton) e.getSource();

        if (buttonSource == buttonReturn) {
            sIdentifier = MainWindow.MAIN_MENU;
        }

        MainWindow.switchToMenu((JPanel) this.getParent(), sIdentifier);
    }
}
