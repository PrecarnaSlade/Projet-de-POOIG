package Domino;

import Common.Deck;
import Common.Game;
import Common.IA;
import Common.Player;

import java.util.Scanner;

public class TerminalDomino extends Game{
    private Scanner scan;
    public TerminalDomino(boolean twoPlayers, Scanner scanner){
        super(null);
        Player[] players = new Player[2];
        players[0] = new Player("Player1");
        if (twoPlayers) {
            players[1] = new Player("Player2");
        } else {
            players[1] = new IA("Player2");
        }
        super.setPlayers(players);
        this.setGrid(new Common.Grid<DominoTile>(11,11));
        this.setDeck(new Deck(30, "Domino"));

        scan= scanner;

        this.afficher();
        this.play(true);
    }

    public void afficher(){
        System.out.println("===============================================================================================================================================");
        for(int y=0; y<11; y++){
            String up="";
            String down="";
            String side1="";
            String side2="";
            String side3="";
            String separator="";

            for(int x=0; x<11; x++){
                DominoTile domino= (DominoTile) this.getGrid().getTileByXY(x,y);
                if(domino==null){
                    up+="           ||";
                    down+="           ||";
                    side1+="           ||";
                    side2+="           ||";
                    side3+="           ||";
                }
                else{
                    for(int i=0;i<3;i++){
                        up+="  "+domino.getSides().getUpSide()[i];
                        down+="  "+domino.getSides().getDownSide()[i];
                    }
                    up+="||";
                    down+="||";
                    side1= domino.getSides().getLeftSide()[0]+"         "+domino.getSides().getRightSide()[0]+"||";
                    side2= domino.getSides().getLeftSide()[1]+"         "+domino.getSides().getRightSide()[1]+"||";
                    side3= domino.getSides().getLeftSide()[2]+"         "+domino.getSides().getRightSide()[2]+"||";
                }
                separator+="=============";
            }
            System.out.println(up+"\n"+side1+"\n"+side2+"\n"+side3+"\n"+down+"\n"+separator);
        }
    }

    public void play(boolean player1){
        play(!player1);
    }

    public static void main(String[] args) {
        Scanner scan= new Scanner(System.in);
        System.out.println("/// Jeu de Domino ///");
        TerminalDomino domino;

        boolean response= false;
        while(!response){
            System.out.println("Voulez-vous jouez seul ou à deux joueur? (1/2)");
            if(scan.nextInt()==1) {domino= new TerminalDomino(false, scan); response= true;}
            else if(scan.nextInt()==2) {domino= new TerminalDomino(true,scan); response= true;}
        }
    }
}
