package Debug;

import Carcassonne.SpecialType;
import Exceptions.UnknownCarcassonneTileException;
import Graphic.Menu.ImageManagement;
import Misc.ColorManagement;
import Misc.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, UnknownCarcassonneTileException {
        BufferedImage image = ImageIO.read(new File("./Data/Resources/frfr.png"));
        SpecialType st = SpecialType.colorToSideType(ColorManagement.getMainColor(ImageManagement.getRegionAroundClick(image, new Position(image.getWidth() / 2, image.getHeight() /2), image.getWidth() / 20)));
        System.out.println(st);

    }
}
