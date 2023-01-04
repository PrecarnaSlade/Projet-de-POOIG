package Graphic.Menu;

import Common.Window.Display;
import Common.Window.MainWindow;
import Misc.WindowManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayMenu extends JPanel implements ActionListener {
    private final JButton buttonDomino, buttonCarcassonne, buttonReturn;

    public PlayMenu() {
        this.setSize(Display.WIDTH, Display.HEIGHT);
        this.setLayout(null);
        this.setOpaque(false);

        Dimension buttonDimension = new Dimension(Display.BUTTON_WIDTH, Display.BUTTON_HEIGHT);

        buttonDomino = new JButton();
        this.add(buttonDomino);
        buttonDomino.setText("Domino");
        buttonDomino.setSize(buttonDimension);
        buttonDomino.setLocation(Display.CENTER_X - (Display.BUTTON_WIDTH + Display.DISTANCE_BETWEEN_BUTTONS / 2), Display.CENTER_Y - Display.PADDING_Y);
        buttonDomino.addActionListener(this);

        buttonCarcassonne = new JButton();
        this.add(buttonCarcassonne);
        buttonCarcassonne.setText("Carcassonne");
        buttonCarcassonne.setSize(buttonDimension);
        buttonCarcassonne.setLocation(Display.CENTER_X + Display.DISTANCE_BETWEEN_BUTTONS / 2, Display.CENTER_Y - Display.PADDING_Y);
        buttonCarcassonne.addActionListener(this);

        buttonReturn = new JButton();
        this.add(buttonReturn);
        buttonReturn.setText("Return");
        buttonReturn.setSize(buttonDimension);
        buttonReturn.setLocation(Display.WIDTH - (Display.BUTTON_WIDTH + Display.DISTANCE_BETWEEN_BUTTONS), Display.HEIGHT - (Display.BUTTON_HEIGHT + Display.DISTANCE_BETWEEN_BUTTONS * 2));
        buttonReturn.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String sIdentifier = "";
        JButton buttonSource = (JButton) e.getSource();
        MainWindow parent = (MainWindow) WindowManagement.getMasterParentWindow(this);

        if (buttonSource == buttonReturn) {
            sIdentifier = MainWindow.MAIN_MENU;
        } else if (buttonSource == buttonDomino || buttonSource == buttonCarcassonne) {
            parent.setGamePlayed(buttonSource.getText());
            sIdentifier = MainWindow.GAME_OPTION_MENU;
        }

        parent.switchToMenu(sIdentifier);
    }
}
