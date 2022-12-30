package Common;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WindowManagement {
    private static final boolean debug = true;

    public static Container getMasterParentWindow(Container startContainer) {
        while (startContainer.getParent() != null) {
            startContainer = startContainer.getParent();
        }
        return startContainer;
    }

    public static void saveImageFromPanel(JPanel panel, String outputName) {
        if (!debug) {
            return;
        }
        BufferedImage bi = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        try {
            File outputfile = new File(outputName);
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
