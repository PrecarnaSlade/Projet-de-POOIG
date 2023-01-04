import Common.Window.MainWindow;
import Domino.TerminalDomino;

import java.awt.*;
import java.util.Scanner;

public class Play {
    public static void main(String[] args) {
        if (args.length == 2) {
            new TerminalDomino(Integer.parseInt(args[0]), Integer.parseInt(args[1]), new Scanner(System.in));
        } else {
            EventQueue.invokeLater(MainWindow::new);
        }
    }
}
