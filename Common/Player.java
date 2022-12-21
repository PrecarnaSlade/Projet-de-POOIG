package Common;

import MathFuncAndObj.Position;
import java.util.ArrayList;
import java.util.Scanner;

public class Player extends InternalObject {
    private final Scanner scanner;
    private ArrayList<Tile> hand;
    private Game game;

    public Player() {
        hand = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void draw(){
        hand.add(game.getDeck().draw());
    }

    public void discard(Common.Tile t){
        hand.remove(t);
        game.getDeck().put(t);
    }

    public void place(Common.Tile t, Position p){
        game.getGrid().place(t,p);
        hand.remove(t);
    }
}

