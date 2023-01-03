package Common;

import Common.Window.MainWindow;
import Exceptions.NoMoreTileInDeckException;
import Graphic.GamePanel;

import java.awt.*;

public class Game<E> {
    private final Player player1;
    private final Player player2;
    private Deck<E> deck;
    private Grid<E> grid;
    private Tile drawnTile;
    private boolean player1Turn;

    protected Game(boolean twoPlayers){
        player1= new Player();
        if (twoPlayers) {
            player2= new Player();
        } else {
            player2 = new IA();
        }
        player1Turn = true;
    }

    public Player getPlayer1() {return player1;}
    public Player getPlayer2() {return player2;}
    public boolean isPlayer1Turn() {return player1Turn;}
    public Deck<E> getDeck() {return deck;}
    public Grid<E> getGrid() {return grid;}
    public void setDeck(Deck<E> deck) {this.deck = deck;}
    public void setGrid(Grid<E> grid) {this.grid = grid;}
    public Tile getDrawnTile() { return drawnTile;}

    public static Game Create(MainWindow mainWindow) {
        String gamePlayed = mainWindow.getGamePlayed();
        Dimension gridDimension = mainWindow.getGridSize();
        int nDeckSize = gridDimension.width * gridDimension.height;
        boolean twoPlayers = mainWindow.isTwoPlayers();

        Game game = new Game<>(twoPlayers);
        Deck deck = new Deck<>(nDeckSize, gamePlayed);
        Grid grid = new Grid<>(gridDimension);
        game.setDeck(deck);
        game.setGrid(grid);

        return game;
    }

    public void draw(MainWindow mainWindow) throws NoMoreTileInDeckException {
        this.drawnTile = deck.draw();
        while (!grid.canPlace(drawnTile)) {
            this.drawnTile = deck.draw();
        }
        mainWindow.updateTileDrawn(this.drawnTile);
    }

    public void nextTurn(MainWindow mainWindow) throws NoMoreTileInDeckException {
        player1Turn = !player1Turn;
        draw(mainWindow);
    }
}
