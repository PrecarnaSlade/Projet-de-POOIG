package Common;

import java.awt.*;

public class Game<E> {
    private final Player player1;
    private final Player player2;
    private Deck<E> deck;
    private Grid<E> grid;

    private Game(boolean twoPlayers){
        player1= new Player();
        if (twoPlayers) {
            player2= new Player();
        } else {
            player2 = new IA();
        }
    }

    public Player getPlayer1() {return player1;}
    public Player getPlayer2() {return player2;}
    public Deck<E> getDeck() {return deck;}
    public Grid<E> getGrid() {return grid;}
    public void setDeck(Deck<E> deck) {this.deck = deck;}
    public void setGrid(Grid<E> grid) {this.grid = grid;}

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
}
