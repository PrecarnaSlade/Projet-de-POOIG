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
    private String gamePlayed;
    private Dimension gridSize;
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

        // basic operations for window
        cardLayout.show(panelMain, MAIN_MENU);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gamePlayed = "";
        gridSize = null;
        twoPlayers = false;
    }

    public HandWindow getHandWindow() {
        return handWindow;
    }

    public void createGameInterface() {
        try {

            createHandDisplay();
            graphicGamePanel = new GamePanel(Game.Create(this), this);
            this.remove(panelMain);
            this.add(graphicGamePanel, GAME_PANEL);
            graphicGamePanel.getGame().draw(this);
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

    public void updateTileDrawn(Tile tileDrawn) {
        handWindow.setDrawnTile(tileDrawn);
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

    public void internalFrameClosing(InternalFrameEvent e) {
System.out.println("Internal frame closing");
}

        public void internalFrameClosed(InternalFrameEvent e) {
System.out.println("Internal frame closed");
}

        public void internalFrameOpened(InternalFrameEvent e) {
System.out.println("Internal frame opened");
}

        public void internalFrameIconified(InternalFrameEvent e) {
System.out.println("Internal frame iconified");
}

        public void internalFrameDeiconified(InternalFrameEvent e) {
System.out.println("Internal frame deiconified");
}

        public void internalFrameActivated(InternalFrameEvent e) {
System.out.println("Internal frame activated");
}

        public void internalFrameDeactivated(InternalFrameEvent e) {
System.out.println("Internal frame deactivated");
}
}
