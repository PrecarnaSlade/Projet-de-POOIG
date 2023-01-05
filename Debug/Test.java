package Debug;

import Carcassonne.SideType;
import Graphic.Menu.ImageManagement;
import Misc.ColorManagement;
import Misc.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new File("./Data/Resources/frfr.png"));
        SideType st = SideType.colorToSideType(ColorManagement.getMainColor(ImageManagement.getRegionAroundClick(image, new Position(image.getWidth() / 2, image.getHeight() /2), image.getWidth() / 20)));
        System.out.println(st);

    }
}
