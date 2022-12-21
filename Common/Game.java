package Common;

public abstract class Game {
    private final Player player1;
    private final Player player2;
    private Deck deck;
    private Grid grid;

    public Game(){
        player1= new Player();
        player2= new Player();
    }

    public Player getPlayer1() {return player1;}
    public Player getPlayer2() {return player2;}
    public Deck getDeck() {return deck;}
    public Grid getGrid() {return grid;}
}
