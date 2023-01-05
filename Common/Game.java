package Common;

import Carcassonne.CarcassonneIA;
import Carcassonne.CarcassonnePlayer;
import Common.Window.MainWindow;
import Exceptions.NoMoreTileInDeckException;

import javax.swing.*;
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
        while (grid.getPositionsAvailable(this.drawnTile).length == 0) {
            this.drawnTile = deck.draw();
        }
        mainWindow.updateTileDrawn(this.drawnTile, players[playerTurn]);
    }

    public void nextTurn(MainWindow mainWindow) throws NoMoreTileInDeckException {
        Tile tilePlaced = this.drawnTile;
        if (mainWindow!=null && mainWindow.getGamePlayed().equals("Domino")) {
            int nScored = grid.getAdjacentTileNb(tilePlaced.getPosition());
            getCurrentPlayer().addPoints(nScored);
            if (!getCurrentPlayer().isIA()) {
                JOptionPane.showMessageDialog(null, "You scored : " + nScored + " points !\n" + getCurrentPlayer().getName() + " will now play");
            }
        } else if (mainWindow!=null && mainWindow.getGamePlayed().equals("Carcassonne")) {
            for (Player p : players) {
                if (!p.isIA()) {
                    ((CarcassonnePlayer) p).setHasPlacedMiple(false);
                }
            }
        }
        playerTurn = (playerTurn + 1) % (players.length);
        if (mainWindow != null) draw(mainWindow);
        if (getCurrentPlayer().isIA()) {
            IA ia = (IA) players[playerTurn];
            if (mainWindow != null) ia.playTurn(this.drawnTile, grid, mainWindow.getGraphicGamePanel().getGrid());
            nextTurn(mainWindow);
        }
    }

    public void nextTurn() throws NoMoreTileInDeckException {
        nextTurn(null);
    }

    public Player getCurrentPlayer() {
        return players[playerTurn];
    }

    public Player getWinner() {
        int nMax = 0;
        Player winner = players[0];
        for (Player p : players) {
            if (p.getPoints() > nMax) {
                winner = p;
                nMax = p.getPoints();
            }
        }
        return winner;
    }

    public String getScoreText() {
        String sBuffer = "";

        for (Player p : players) {
            sBuffer += p.getName() + " : " + p.getPoints() + "\n";
        }
        return sBuffer;
    }
}
