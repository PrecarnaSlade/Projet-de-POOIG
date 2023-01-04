package Common.Window;

import Common.Game;
import Common.Player;
import Common.Tile;
import Exceptions.NoMoreTileInDeckException;
import Graphic.GamePanel;
import Graphic.Menu.*;
import Graphic.TileGraphic;

import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import java.awt.*;
import java.io.IOException;

public class MainWindow extends JFrame implements InternalFrameListener {
    private final JPanel panelMain;
    private final CardLayout cardLayout;
    private MainMenu graphicMainMenu;
    private PlayMenu graphicPlayMenu;
    private OptionMenu graphicOptionMenu;
    private GameOptionMenu graphicGameOptionMenu;
    private GamePanel graphicGamePanel;
    private HandWindow handWindow;
    private PlayerSelectionMenu playerSelectionMenu;
    private String gamePlayed;
    private Dimension gridSize;
    private Player[] players;
    public static final String MAIN_MENU = "Main_Menu";
    public static final String PLAY_MENU = "Play_Menu";
    public static final String OPTION_MENU = "Option_Menu";
    public static final String GAME_OPTION_MENU = "Game_Option_Menu";
    public static final String GAME_PANEL = "Game_Panel";
    public static final String PLAYER_SELECTION_MENU = "Player_Selection_Menu";

    public MainWindow() {
        // window creation
        panelMain = new JPanel();
        this.setResizable(false);
        this.setSize(Display.WIDTH, Display.HEIGHT);
        this.setLayout(null);
        this.add(panelMain);
        this.setTitle("POO 2022/2023");
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

        // PlayerSelectionMenu creation
        playerSelectionMenu = new PlayerSelectionMenu();
        panelMain.add(playerSelectionMenu, PLAYER_SELECTION_MENU);

        // basic operations for window
        cardLayout.show(panelMain, MAIN_MENU);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gamePlayed = "";
        gridSize = null;
        players = null;
    }

    public HandWindow getHandWindow() {
        return handWindow;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void createGameInterface() {
        try {

            createHandDisplay();
            graphicGamePanel = new GamePanel(Game.Create(this), this);
            this.remove(panelMain);
            this.add(graphicGamePanel, GAME_PANEL);
            try {
                graphicGamePanel.getGame().draw(this);
            } catch (NoMoreTileInDeckException e) {
                e.printStackTrace();
            }
            graphicGamePanel.updateImage();
            Insets insets = this.getInsets();
            graphicGamePanel.setLocation(insets.left, insets.top);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createHandDisplay() {
        try {
            this.handWindow = new HandWindow();
            this.add(handWindow);
            this.handWindow.setVisible(true);
            this.handWindow.setLayer(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateTileDrawn(Tile tileDrawn, Player player) {
        handWindow.setDrawnTile(tileDrawn, player);
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
        this.playerSelectionMenu = new PlayerSelectionMenu();
        this.panelMain.add(graphicMainMenu, MAIN_MENU);
        this.panelMain.add(graphicPlayMenu, PLAY_MENU);
        this.panelMain.add(graphicOptionMenu, OPTION_MENU);
        this.panelMain.add(graphicGameOptionMenu, GAME_OPTION_MENU);
        this.panelMain.add(playerSelectionMenu, PLAYER_SELECTION_MENU);
        cardLayout.show(panelMain, OPTION_MENU);

        this.setResizable(false);
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {}
    @Override
    public void internalFrameClosing(InternalFrameEvent e) {}
    @Override
    public void internalFrameClosed(InternalFrameEvent e) {}
    @Override
    public void internalFrameIconified(InternalFrameEvent e) {}
    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {}
    @Override
    public void internalFrameActivated(InternalFrameEvent e) {}
    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {}
}
