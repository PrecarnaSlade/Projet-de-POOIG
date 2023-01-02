package Debug;

import Common.Sides;
import Common.Tile;
import Common.Window.HandWindow;
import Common.Window.MainWindow;
import Domino.DominoTile;
import Graphic.Menu.MainMenu;
import Graphic.TileGraphic;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;

public class Test extends JFrame {

    public static void main(String[] args) {
//        JFrame win = new JFrame();
//        win.setSize(600,600);
//        win.setLayout(new CardLayout());
//        int[] up = {0,0,0};
//        int[] right = {1,1,1};
//        int[] down = {2,2,2};
//        int[] left = {3,3,3};
//        DominoTile tile = new DominoTile();
//        TileGraphic graph = tile.getGraphic();
//        JLabel label = new JLabel(new ImageIcon(Common.Window.Management.panelToBufferedImage(graph)));
//        win.add(label);
//        win.add(graph);
//        graph.setLocation(250, 250);
//        win.setVisible(true);
//        win.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        System.out.println(tile.getGraphicalRepresentation());

          EventQueue.invokeLater(MainWindow::new);

    }
}
