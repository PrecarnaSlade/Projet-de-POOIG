package Debug;

import Common.Sides;
import Graphic.MainMenu;
import Graphic.Tile;

import javax.swing.*;

public class Test extends JFrame {

    public static void main(String[] args) {
        JFrame win = new JFrame();
        win.setSize(600,600);
        win.setLayout(null);
//        int[] up = {0,0,0};
//        int[] right = {1,1,1};
//        int[] down = {2,2,2};
//        int[] left = {3,3,3};
//        Tile tile = new Tile("domino", 600, 600, 0, new Sides<>(up,right,down,left), 0);
//        win.add(tile);
//        tile.setLocation(250, 250);
        MainMenu menu = new MainMenu(600, 600);
        win.add(menu);
        win.setVisible(true);
        win.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
