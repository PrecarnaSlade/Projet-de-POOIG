package Debug;

import Carcassonne.SpecialType;
import Domino.TerminalDomino;
import Exceptions.UnknownCarcassonneTileException;
import Graphic.Menu.ImageManagement;
import Misc.ColorManagement;
import Misc.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException, UnknownCarcassonneTileException {
        new TerminalDomino(1, 1, new Scanner(System.in));

    }
}
