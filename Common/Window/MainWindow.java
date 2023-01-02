package Common.Window;

import Common.Game;
import Common.Tile;
import Graphic.GamePanel;
import Graphic.Menu.GameOptionMenu;
import Graphic.Menu.MainMenu;
import Graphic.Menu.OptionMenu;
import Graphic.Menu.PlayMenu;
import Graphic.TileGraphic;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainWindow extends JFrame {
    private final JPanel panelMain;
    private final CardLayout cardLayout;
    private MainMenu graphicMainMenu;
    private PlayMenu graphicPlayMenu;
    private OptionMenu graphicOptionMenu;
    private GameOptionMenu graphicGameOptionMenu;
    private GamePanel graphicGamePanel;
    private String gamePlayed;
    private Dimension gridSize;
    private JPanel hand;
    private boolean twoPlayers;
    public static final String MAIN_MENU = "Main_Menu";
    public static final String PLAY_MENU = "Play_Menu";
    public static final String OPTION_MENU = "Option_Menu";
    public static final String GAME_OPTION_MENU = "Game_Option_Menu";
    public static final String GAME_PANEL = "Game_Panel";

    public MainWindow() {
        // window creation
        panelMain = new JPanel();
        this.setResizable(false);
        this.setSize(Display.WIDTH, Display.HEIGHT);
        this.setLayout(new CardLayout());
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
        twoPlayers = false;
    }

    public void createGameInterface() {
        try {
            graphicGamePanel = new GamePanel(Game.Create(this), this);
            this.remove(panelMain);
            this.add(graphicGamePanel, GAME_PANEL);
            createHandDisplay();
            graphicGamePanel.getGame().draw(this);
            graphicGamePanel.updateImage();
            Insets insets = this.getInsets();
            graphicGamePanel.setLocation(insets.left, insets.top);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createHandDisplay() {
        hand = new JPanel();
        this.add(hand);
        hand.setSize(Display.TILE_SIZE + Display.DISTANCE_BETWEEN_BUTTONS * 2, Display.TILE_SIZE + Display.DISTANCE_BETWEEN_BUTTONS * 2);
        hand.setLayout(null);;
        JLabel infoLabel = new JLabel("You drew this tile :");
        hand.add(infoLabel);
        infoLabel.setLocation(Display.DISTANCE_BETWEEN_BUTTONS / 4, Display.DISTANCE_BETWEEN_BUTTONS / 4);
        hand.setBackground(Color.red);
        hand.setLocation(Display.WIDTH - Display.TILE_SIZE - Display.DISTANCE_BETWEEN_BUTTONS, Display.HEIGHT - Display.TILE_SIZE - Display.DISTANCE_BETWEEN_BUTTONS);
    }

    public void updateTileDrawn(Tile tileDrawn) {
        TileGraphic graphic = tileDrawn.getGraphic();
        hand.add(graphic);
        graphic.setSize(Display.TILE_SIZE, Display.TILE_SIZE);
        graphic.setLocation(Display.DISTANCE_BETWEEN_BUTTONS, Display.DISTANCE_BETWEEN_BUTTONS);
        graphic.setToolTipText("Press R to rotate clockwise\nShift + R to rotate anti-clockwise");
    }

//    public void updateFront() {
//        JLabel oTemp = new JLabel();
//        oTemp.setSize(this.getSize());
//        oTemp.setIcon(new ImageIcon(graphicGamePanel.getContent()));
//        this.add(oTemp);
//        oTemp.setLocation(0,0);
//    }

    public boolean isTwoPlayers() {
        return twoPlayers;
    }

    public void setTwoPlayers(boolean twoPlayers) {
        this.twoPlayers = twoPlayers;
    }

    public void setGridSize(Dimension gridSize) {
        this.gridSize = gridSize;
    }

    public void setGamePlayed(String gamePlayed) {
        this.gamePlayed = gamePlayed;
        this.graphicGameOptionMenu.setLabelGamePlayedText(gamePlayed);
        this.setTitle(gamePlayed);
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

        this.setResizable(true);

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

        this.setResizable(false);
    }
}
