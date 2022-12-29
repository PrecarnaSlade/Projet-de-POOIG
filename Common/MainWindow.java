package Common;

import Graphic.MainMenu;
import Graphic.PlayMenu;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public static final String MAIN_MENU = "Main_Menu";
    public static final String PLAY_MENU = "Play_Menu";

    public MainWindow() {
        // window creation
        JPanel panelMain = new JPanel();
        this.setSize(Display.WIDTH.getValue(), Display.HEIGHT.getValue());
        this.setLayout(null);
        this.add(panelMain);
        CardLayout cardLayout = new CardLayout();
        panelMain.setLayout(cardLayout);
        panelMain.setSize(Display.WIDTH.getValue(), Display.HEIGHT.getValue());

        // MainMenu creation
        MainMenu graphicMainMenu = new MainMenu(Display.WIDTH.getValue(), Display.HEIGHT.getValue());
        panelMain.add(graphicMainMenu, MAIN_MENU);

        //PlayMenu creation
        PlayMenu graphicPlayMenu = new PlayMenu(Display.WIDTH.getValue(), Display.HEIGHT.getValue());
        panelMain.add(graphicPlayMenu, PLAY_MENU);

        // basic operations for window
        cardLayout.show(panelMain, MAIN_MENU);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void switchToMenu(JPanel displayPanel, String menuIdentifier) {
        if (menuIdentifier.equals("")) {
            return;
        }
        CardLayout cardLayout = (CardLayout) displayPanel.getLayout();
        cardLayout.show(displayPanel, menuIdentifier);
    }
}
