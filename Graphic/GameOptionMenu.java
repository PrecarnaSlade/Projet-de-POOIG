package Graphic;

import Common.Display;
import Common.MainWindow;
import Common.WindowManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOptionMenu extends JPanel implements ActionListener {
    JButton buttonReturn, buttonPlay;
    JComboBox<String> comboBoxGridSize;

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

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String sIdentifier = "";
        JButton buttonSource = (JButton) e.getSource();

        if (buttonSource == buttonReturn) {
            sIdentifier = MainWindow.MAIN_MENU;
        } else if (buttonSource == buttonPlay) {
            // creation of the game
            MainWindow parent = (MainWindow) WindowManagement.getMasterParentWindow(this);
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
            // Need to switch to game graphic !!!!
        }

        MainWindow.switchToMenu((JPanel) this.getParent(), sIdentifier);
    }
}
