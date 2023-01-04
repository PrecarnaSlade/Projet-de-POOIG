//package Domino;
//
//import Common.*;
//import Exceptions.InvalidMoveException;
//import Exceptions.NoMoreTileInDeckException;
//
//import java.util.Scanner;
//
//public class TerminalDomino extends Game{
//    private Scanner scan;
//    public TerminalDomino(int playerNumber, int IaNumber, Scanner scanner){
//        super(null);
//        Player[] players = new Player[playerNumber + IaNumber];
//        for (int i = 0; i < playerNumber; i++) {
//            players[i] = new Player("Player" + (i + 1));
//        }
//        for (int i = playerNumber; i < players.length; i++) {
//            players[i] = new IA("IA" + (i - playerNumber + 1));
//        }
//        super.setPlayers(players);
//        this.setGrid(new Common.Grid<DominoTile>(11,11));
//        this.setDeck(new Deck(30, "Domino"));
//
//        scan= scanner;
//
//        this.getGrid().setUp(new DominoTile());
//        this.afficher();
//        this.play();
//    }
//
//    public void afficher(){
//        System.out.println("===============================================================================================================================================");
//        for(int y=0; y<11; y++){
//            String up="";
//            String down="";
//            String side1="";
//            String side2="";
//            String side3="";
//            String separator="";
//
//            for(int x=0; x<11; x++){
//                DominoTile domino= (DominoTile) this.getGrid().getTileByXY(x,y);
//                if(domino==null){
//                    up+="           ||";
//                    down+="           ||";
//                    side1+="           ||";
//                    side2+="           ||";
//                    side3+="           ||";
//                }
//                else{
//                    for(int i=0;i<3;i++){
//                        up+="  "+domino.getSides().getUpSide()[i];
//                        down+="  "+domino.getSides().getDownSide()[i];
//                    }
//                    up+="  ||";
//                    down+="  ||";
//                    side1+= domino.getSides().getLeftSide()[0]+"         "+domino.getSides().getRightSide()[0]+"||";
//                    side2+= domino.getSides().getLeftSide()[1]+"         "+domino.getSides().getRightSide()[1]+"||";
//                    side3+= domino.getSides().getLeftSide()[2]+"         "+domino.getSides().getRightSide()[2]+"||";
//                }
//                separator+="=============";
//            }
//            System.out.println(up+"\n"+side1+"\n"+side2+"\n"+side3+"\n"+down+"\n"+separator);
//        }
//    }
//
//    public void play(){
//        try {
//            String player = (this.getCurrentPlayer()==this.getPlayer1()? "Joueur 1" : "Joueur 2");
//            System.out.println("/// Tour du " + player + " ///");
//            System.out.println("Score: " + (this.getCurrentPlayer().getPoints()));
//            System.out.println("Pièce actuelle:\n");
//            DominoTile domino = (DominoTile) this.getDeck().draw();
//            System.out.println(domino.getGraphicalRepresentation());
//            this.askMove(domino);
//            nextPlayer();
//            play();
//        } catch(NoMoreTileInDeckException e){
//            endGame();
//        }
//    }
//
//    public void endGame(){
//        System.out.println((this.getPlayer1().getPoints()>this.getPlayer2().getPoints()?"Le Joueur 1 a gagné!":"Le Joueur 2 a gagné!"));
//        System.out.println("Score:\nJ1: " + this.getPlayer1().getPoints() +"\nJ2: " + this.getPlayer2().getPoints());
//        System.exit(0);
//    }
//
//    private void askMove(DominoTile domino){
//        System.out.println("Choisissez une action: poser (p) / rotation horaire (r) / rotation anti-horaire (ra) / défausser (d) / arrêter (a)");
//        switch(scan.next()){
//            case "p":
//                askPlacement(domino);
//                this.afficher();
//                break;
//            case "r":
//                domino.rotateClockwise();
//                System.out.println(domino.getGraphicalRepresentation());
//                askMove(domino);
//                break;
//            case "ra":
//                domino.rotateAntiClockwise();
//                System.out.println(domino.getGraphicalRepresentation());
//                askMove(domino);
//                break;
//            case "d":
//                this.getDeck().add(domino);
//                break;
//            case "a":
//                endGame();
//                break;
//            default: askMove(domino); break;
//        }
//    }
//
//    private void askPlacement(DominoTile domino){
//        System.out.println("Donner la position en X: ");
//        int x= scan.nextInt();
//        System.out.println("Donner la position en Y: ");
//        int y= scan.nextInt();
//        try{
//            this.getGrid().place(domino,x,y);
//            this.countPoints(x,y);
//        }
//        catch (InvalidMoveException e){
//            System.out.println("Ce placement n'est pas possible, veuillez réessayer.");
//            askMove(domino);
//        }
//    }
//
//    private void countPoints(int x, int y){
//        int sum=0;
//        DominoTile downTile= (DominoTile) this.getGrid().getTileByXY(x,y-1);
//        if(downTile!=null) sum+= this.countPoints(downTile.getUpSide());
//        DominoTile leftTile= (DominoTile) this.getGrid().getTileByXY(x-1,y);
//        if(leftTile!=null) sum+= this.countPoints(leftTile.getRightSide());
//        DominoTile upTile= (DominoTile) this.getGrid().getTileByXY(x,y+1);
//        if(upTile!=null) sum+= this.countPoints(upTile.getDownSide());
//        DominoTile rightTile= (DominoTile) this.getGrid().getTileByXY(x+1,y);
//        if(rightTile!=null) sum+= this.countPoints(rightTile.getLeftSide());
//
//        this.getCurrentPlayer().addPoints(sum);
//    }
//
//    private int countPoints(int[] side){
//        int sum=0;
//        for(int i: side){
//            sum+=i;
//        }
//        return sum;
//    }
//
//    public static void main(String[] args) {
//        Scanner scan= new Scanner(System.in);
//        System.out.println("/// Jeu de Domino ///");
//        TerminalDomino domino;
//
//        boolean response= false;
//        while(!response){
//            System.out.println("Voulez-vous jouez seul ou à deux joueur? (1/2)");
//            if(scan.nextInt()==1) {domino= new TerminalDomino(false, scan); response= true;}
//            else if(scan.nextInt()==2) {domino= new TerminalDomino(true,scan); response= true;}
//        }
//    }
//}
