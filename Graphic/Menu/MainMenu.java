package Graphic.Menu;

import Common.Window.Display;
import Common.Window.MainWindow;
import Common.Window.Management;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JPanel implements ActionListener {
    private JButton buttonPlay;
    private JButton buttonOption;
    private JButton buttonExit;

    public MainMenu() {
        File oFileHandler;
        String imagePath = "";
        this.setSize(Display.WIDTH, Display.HEIGHT);
        this.setLayout(null);
        this.setOpaque(false);
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
        Dimension dimensionButtons = new Dimension(Display.BUTTON_WIDTH, Display.BUTTON_HEIGHT);

        buttonPlay = new JButton();
        this.add(buttonPlay);
        buttonPlay.setText("Play");
        buttonPlay.setSize(dimensionButtons);
        buttonPlay.setLocation(Display.CENTER_X - Display.PADDING_X, Display.CENTER_Y - Display.PADDING_Y - (Display.DISTANCE_BETWEEN_BUTTONS + Display.PADDING_Y * 2));
        buttonPlay.addActionListener(this);

        buttonOption = new JButton();
        this.add(buttonOption);
        buttonOption.setText("Option");
        buttonOption.setSize(dimensionButtons);
        buttonOption.setLocation(Display.CENTER_X - Display.PADDING_X, Display.CENTER_Y - Display.PADDING_Y);
        buttonOption.addActionListener(this);

        buttonExit = new JButton();
        this.add(buttonExit);
        buttonExit.setText("Exit");
        buttonExit.setSize(dimensionButtons);
        buttonExit.setLocation(Display.CENTER_X - Display.PADDING_X, Display.CENTER_Y - Display.PADDING_Y + (Display.DISTANCE_BETWEEN_BUTTONS + Display.PADDING_Y * 2));
        buttonExit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String sIdentifier = "";
        JButton buttonSource = (JButton) e.getSource();
        MainWindow parent = (MainWindow) Management.getMasterParentWindow(this);

        if (buttonSource == buttonPlay) {
            sIdentifier = MainWindow.PLAY_MENU;
        } else if (buttonSource == buttonExit) {
            System.exit(0);
        } else if (buttonSource == buttonOption) {
            sIdentifier = MainWindow.OPTION_MENU;
        }

        parent.switchToMenu(sIdentifier);
    }
}
