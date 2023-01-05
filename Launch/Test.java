package Launch;

import Common.Window.MainWindow;

import javax.swing.*;
import java.awt.*;

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
