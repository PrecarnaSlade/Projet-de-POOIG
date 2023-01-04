package Common;

import Common.Window.MainWindow;
import Exceptions.NoMoreTileInDeckException;
import Graphic.GamePanel;

import java.awt.*;

public class Game<E> {
    private Player[] players;
    private Deck<E> deck;
    private Grid<E> grid;
    private Tile drawnTile;
    private int playerTurn;

    protected Game(Player[] players){
        this.players = players;
        playerTurn = 0;
    }

    public Player[] getPlayers() {return players;}
    public int getPlayerTurn() {return playerTurn;}
    public void setPlayers(Player[] players) {this.players = players;}
    public Deck<E> getDeck() {return deck;}
    public Grid<E> getGrid() {return grid;}
    public void setDeck(Deck<E> deck) {this.deck = deck;}
    public void setGrid(Grid<E> grid) {this.grid = grid;}
    public Tile getDrawnTile() { return drawnTile;}

    public static Game Create(MainWindow mainWindow) {
        String gamePlayed = mainWindow.getGamePlayed();
        Dimension gridDimension = mainWindow.getGridSize();
        int nDeckSize = gridDimension.width * gridDimension.height;

        Game game = new Game<>(mainWindow.getPlayers());
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
        mainWindow.updateTileDrawn(this.drawnTile, players[playerTurn]);
    }

    public void nextTurn(MainWindow mainWindow) throws NoMoreTileInDeckException {
        playerTurn = (playerTurn + 1) % (players.length - 1);
        draw(mainWindow);
    }

    public Player getCurrentPlayer() {
        return players[playerTurn];
    }
}
