package Common.Window;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Management {
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
        panel.paint(bi.getGraphics());
        try {
            File outputFile = new File(outputName);
            ImageIO.write(bi, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
