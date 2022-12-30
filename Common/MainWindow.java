package Common;

import Graphic.Menu.GameOptionMenu;
import Graphic.Menu.MainMenu;
import Graphic.Menu.OptionMenu;
import Graphic.Menu.PlayMenu;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final JPanel panelMain;
    private final CardLayout cardLayout;
    private MainMenu graphicMainMenu;
    private PlayMenu graphicPlayMenu;
    private OptionMenu graphicOptionMenu;
    private GameOptionMenu graphicGameOptionMenu;
    private String gamePlayed;
    private Dimension gridSize;
    public static final String MAIN_MENU = "Main_Menu";
    public static final String PLAY_MENU = "Play_Menu";
    public static final String OPTION_MENU = "Option_Menu";
    public static final String GAME_OPTION_MENU = "Game_Option_Menu";

    public MainWindow() {
        // window creation
        panelMain = new JPanel();
        this.setSize(Display.WIDTH, Display.HEIGHT);
        this.setLayout(null);
        this.add(panelMain);
        this.setTitle("Best game 2023");
        cardLayout = new CardLayout();
        panelMain.setLayout(cardLayout);
        panelMain.setSize(Display.WIDTH, Display.HEIGHT);

        // MainMenu creation
        graphicMainMenu = new MainMenu();
        panelMain.add(graphicMainMenu, MAIN_MENU);

        //PlayMenu creation
        graphicPlayMenu = new PlayMenu();
        panelMain.add(graphicPlayMenu, PLAY_MENU);

        //OptionMenu creation
        graphicOptionMenu = new OptionMenu();
        panelMain.add(graphicOptionMenu, OPTION_MENU);

        //GameOptionMenu creation
        graphicGameOptionMenu = new GameOptionMenu();
        panelMain.add(graphicGameOptionMenu, GAME_OPTION_MENU);

        // basic operations for window
        cardLayout.show(panelMain, MAIN_MENU);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gamePlayed = "";
        gridSize = null;
    }

    public void setGridSize(Dimension gridSize) {
        this.gridSize = gridSize;
    }

    public void setGamePlayed(String gamePlayed) {
        this.gamePlayed = gamePlayed;
        this.graphicGameOptionMenu.setLabelGamePlayedText(gamePlayed);
    }

    public Dimension getGridSize() {
        return gridSize;
    }

    public String getGamePlayed() {
        return gamePlayed;
    }

    public void switchToMenu(String menuIdentifier) {
        if (menuIdentifier.equals("")) {
            return;
        }
        CardLayout cardLayout = (CardLayout) panelMain.getLayout();
        cardLayout.show(panelMain, menuIdentifier);
    }

    public void Resize(int width, int height) {
        Dimension ScreenDefinition = Toolkit.getDefaultToolkit().getScreenSize();
        if (width < 600 || height < 600 || width > ScreenDefinition.width || height > ScreenDefinition.height) {
            JOptionPane.showMessageDialog(panelMain, "Incorrect size entered. Please put a reasonable size according to your screen definition.");
            return;
        }

        // deleting components
        Component[] components = panelMain.getComponents();
        for (Component component : components) {
            panelMain.remove(component);
        }

        // setting up new base sizes
        Display.setWidth(width);
        Display.setHeight(height);

        this.setSize(width, height);
        this.panelMain.setSize(width, height);
        this.graphicMainMenu = new MainMenu();
        this.graphicPlayMenu = new PlayMenu();
        this.graphicOptionMenu = new OptionMenu();
        this.graphicGameOptionMenu = new GameOptionMenu();
        this.panelMain.add(graphicMainMenu, MAIN_MENU);
        this.panelMain.add(graphicPlayMenu, PLAY_MENU);
        this.panelMain.add(graphicOptionMenu, OPTION_MENU);
        this.panelMain.add(graphicGameOptionMenu, GAME_OPTION_MENU);
        cardLayout.show(panelMain, OPTION_MENU);
    }
}
