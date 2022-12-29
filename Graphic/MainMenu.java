package Graphic;

import Common.Display;
import Common.MainWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JPanel implements ActionListener {
    JButton buttonPlay;
    JButton buttonOption;
    JButton buttonExit;

    public MainMenu(int width, int height) {
        File oFileHandler;
        String imagePath = "";
        this.setSize(width, height);
        this.setLayout(null);
        try {
            // Image creation
            oFileHandler = new File(imagePath);
            BufferedImage backgroundImage = ImageIO.read(oFileHandler);
            JLabel oPicture = new JLabel(new ImageIcon(backgroundImage));
            this.add(oPicture);
            oPicture.setLocation(0,0);

        } catch (IOException e) {
            System.out.println("MainMenu : imagePath error");
            e.printStackTrace();
        }
        Dimension dimensionButtons = new Dimension(Display.BUTTON_WIDTH.getValue(), Display.BUTTON_HEIGHT.getValue());

        buttonPlay = new JButton();
        this.add(buttonPlay);
        buttonPlay.setText("Play");
        buttonPlay.setSize(dimensionButtons);
        buttonPlay.setLocation(Display.CENTER_X.getValue() - Display.PADDING_X.getValue(), Display.CENTER_Y.getValue() - Display.PADDING_Y.getValue() - (Display.DISTANCE_BETWEEN_BUTTONS.getValue() + Display.PADDING_Y.getValue() * 2));
        buttonPlay.addActionListener(this);

        buttonOption = new JButton();
        this.add(buttonOption);
        buttonOption.setText("Option");
        buttonOption.setSize(dimensionButtons);
        buttonOption.setLocation(Display.CENTER_X.getValue() - Display.PADDING_X.getValue(), Display.CENTER_Y.getValue() - Display.PADDING_Y.getValue());
        buttonOption.addActionListener(this);

        buttonExit = new JButton();
        this.add(buttonExit);
        buttonExit.setText("Exit");
        buttonExit.setSize(dimensionButtons);
        buttonExit.setLocation(Display.CENTER_X.getValue() - Display.PADDING_X.getValue(), Display.CENTER_Y.getValue() - Display.PADDING_Y.getValue() + (Display.DISTANCE_BETWEEN_BUTTONS.getValue() + Display.PADDING_Y.getValue() * 2));
        buttonExit.addActionListener(this);
    }

    public void unloadMainMenu() {
        buttonPlay.setVisible(false);
        buttonOption.setVisible(false);
        buttonExit.setVisible((false));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String sIdentifier = "";
        JButton buttonSource = (JButton) e.getSource();

        if (buttonSource == buttonPlay) {
            sIdentifier = MainWindow.PLAY_MENU;
        } else if (buttonSource == buttonExit) {
            System.exit(0);
        }

        MainWindow.switchToMenu((JPanel) this.getParent(), sIdentifier);
    }
}
