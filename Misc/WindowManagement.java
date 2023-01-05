package Misc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WindowManagement {

    public static Container getMasterParentWindow(Container startContainer) {
        while (startContainer.getParent() != null) {
            startContainer = startContainer.getParent();
        }
        return startContainer;
    }

}
