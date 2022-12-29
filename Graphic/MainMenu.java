package Graphic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JPanel {

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

        int nCenterX = width / 2;
        int nCenterY = height / 2;
        int nPaddingX = width / 3;
        int nPaddingY = height / 6;
        int nDistanceBetweenButton = nPaddingY / 3;
        Dimension dimensionButtons = new Dimension(nPaddingX, nPaddingY);
        nPaddingX /= 2;
        nPaddingY /= 2;

        JButton buttonPlay = new JButton();
        this.add(buttonPlay);
        buttonPlay.setText("Play");
        buttonPlay.setSize(dimensionButtons);
        buttonPlay.setLocation(nCenterX - nPaddingX, nCenterY - nPaddingY - (nDistanceBetweenButton + nPaddingY * 2));

        JButton buttonOption = new JButton();
        this.add(buttonOption);
        buttonOption.setText("Option");
        buttonOption.setSize(dimensionButtons);
        buttonOption.setLocation(nCenterX - nPaddingX, nCenterY - nPaddingY);

        JButton buttonExit = new JButton();
        this.add(buttonExit);
        buttonExit.setText("Exit");
        buttonExit.setSize(dimensionButtons);
        buttonExit.setLocation(nCenterX - nPaddingX, nCenterY - nPaddingY + (nDistanceBetweenButton + nPaddingY * 2));
    }

}
