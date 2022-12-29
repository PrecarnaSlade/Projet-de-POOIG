package Common;

import java.awt.*;

public class WindowManagement {

    public static Container getMasterParentWindow(Container startContainer) {
        while (startContainer.getParent() != null) {
            startContainer = startContainer.getParent();
        }
        return startContainer;
    }
}
