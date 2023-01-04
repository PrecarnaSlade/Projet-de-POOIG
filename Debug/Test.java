package Debug;

import Carcassonne.CarcassonneTile;
import Common.Sides;
import Common.Tile;
import Common.Window.HandWindow;
import Common.Window.MainWindow;
import Domino.DominoTile;
import Graphic.Menu.MainMenu;
import Graphic.Menu.PlayerSelectionMenu;
import Graphic.TileGraphic;
import com.sun.tools.javac.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Test extends JFrame {

    public static void main(String[] args) {
//        JFrame win = new JFrame();
//        win.setSize(600,600);
//        win.setLayout(new BorderLayout());
//        CarcassonneTile tile = new CarcassonneTile();
//        win.add(tile.getGraphic(), BorderLayout.CENTER);
//        win.setVisible(true);
//        win.setDefaultCloseOperation(EXIT_ON_CLOSE);


//          EventQueue.invokeLater(() -> {
//              HandWindow w = new HandWindow();
//              DominoTile tile = new DominoTile();
//              w.setDrawnTile(tile);
//          });

        EventQueue.invokeLater(MainWindow::new);

    }
}
