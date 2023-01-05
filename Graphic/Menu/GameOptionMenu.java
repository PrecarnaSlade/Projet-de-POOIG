package Graphic.Menu;

import Common.IA;
import Common.Player;
import Common.Window.Display;
import Common.Window.MainWindow;
import Misc.WindowManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GameOptionMenu extends JPanel implements ActionListener {
    private final JButton buttonReturn, buttonPlay, buttonOption;
    private final JComboBox<String> comboBoxGridSize;
    private final JLabel labelGamePlayed;

    public GameOptionMenu() {
        this.setSize(Display.WIDTH, Display.HEIGHT);
        this.setLayout(null);

        Dimension buttonDimension = new Dimension(Display.BUTTON_WIDTH, Display.BUTTON_HEIGHT);


        int nMinimumSize = 11;
        int nPadding = 10;
        int nSizeNumber = 10;
        int nResolution;
        String[] aScreenSize = new String[nSizeNumber];
        String sSelectedItem = "11x11";
        String sTemp;

        for (int i = 0; i < nSizeNumber ; i++) {
            nResolution = nMinimumSize + nPadding * i;
            sTemp = nResolution + "x" + nResolution;
            aScreenSize[i] = sTemp;
        }

        labelGamePlayed = new JLabel();
        this.add(labelGamePlayed);
        labelGamePlayed.setSize(buttonDimension.width / 2, buttonDimension.height / 2);
        labelGamePlayed.setLocation(Display.CENTER_X - Display.PADDING_X, Display.DISTANCE_BETWEEN_BUTTONS);

        JLabel label = new JLabel("Grid size : ");
        this.add(label);
        label.setSize(buttonDimension.width / 2, buttonDimension.height / 2);
        label.setLocation(Display.CENTER_X - (Display.BUTTON_WIDTH + Display.DISTANCE_BETWEEN_BUTTONS / 2), Display.CENTER_Y - Display.PADDING_Y - Display.DISTANCE_BETWEEN_BUTTONS - label.getHeight());

        comboBoxGridSize = new JComboBox<>(aScreenSize);
        this.add(comboBoxGridSize);
        comboBoxGridSize.setSize(buttonDimension);
        comboBoxGridSize.setLocation(Display.CENTER_X - (Display.BUTTON_WIDTH + Display.DISTANCE_BETWEEN_BUTTONS / 2), Display.CENTER_Y - Display.PADDING_Y);
        comboBoxGridSize.setSelectedItem(sSelectedItem);

        buttonPlay = new JButton();
        this.add(buttonPlay);
        buttonPlay.setText("Play");
        buttonPlay.setSize(buttonDimension);
        buttonPlay.setLocation(Display.CENTER_X + Display.DISTANCE_BETWEEN_BUTTONS / 2, Display.CENTER_Y - Display.PADDING_Y);
        buttonPlay.addActionListener(this);

        buttonReturn = new JButton();
        this.add(buttonReturn);
        buttonReturn.setText("Return");
        buttonReturn.setSize(buttonDimension);
        buttonReturn.setLocation(Display.WIDTH - (Display.BUTTON_WIDTH + Display.DISTANCE_BETWEEN_BUTTONS), Display.HEIGHT - (Display.BUTTON_HEIGHT + Display.DISTANCE_BETWEEN_BUTTONS * 2));
        buttonReturn.addActionListener(this);

        buttonOption = new JButton();
        this.add(buttonOption);
        buttonOption.setText("Option");
        buttonOption.setSize(buttonDimension);
        buttonOption.setLocation(Display.DISTANCE_BETWEEN_BUTTONS, Display.HEIGHT - (Display.BUTTON_HEIGHT + Display.DISTANCE_BETWEEN_BUTTONS * 2));
        buttonOption.addActionListener(this);

    }

    public void setLabelGamePlayedText(String labelGamePlayedText) {
        this.labelGamePlayed.setText(labelGamePlayedText);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String sIdentifier = "";
        JButton buttonSource = (JButton) e.getSource();
        MainWindow parent = (MainWindow) WindowManagement.getMasterParentWindow(this);

        if (buttonSource == buttonReturn) {
            sIdentifier = MainWindow.PLAY_MENU;
        } else if (buttonSource == buttonPlay) {
            // creation of the game
            String sSizeSelected = (String) comboBoxGridSize.getSelectedItem();
            assert sSizeSelected != null;
            String[] aSize = sSizeSelected.split("x");
            if (aSize.length != 2) {
                JOptionPane.showMessageDialog(parent, "Incorrect size entered.");
                return;
            }
            int nWidth = Integer.parseInt(aSize[0]);
            int nHeight = Integer.parseInt(aSize[1]);
            Dimension oGridDimension = new Dimension(nWidth, nHeight);
            parent.setGridSize(oGridDimension);
            if (parent.getPlayers() == null) {
                Player[] players = {new Player("Player1"), new IA("Player2")};
                parent.setPlayers(players);
            }
            parent.createGameInterface();
            sIdentifier = MainWindow.GAME_PANEL;
        } else if (buttonSource == buttonOption) {
            sIdentifier = MainWindow.PLAYER_SELECTION_MENU;
        }

        parent.switchToMenu(sIdentifier);
    }
}
