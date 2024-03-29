package Common.Window;

public class Display {
    public static int WIDTH = 600;
    public static int HEIGHT = (600);
    public static int CENTER_X = (WIDTH / 2);
    public static int CENTER_Y = (HEIGHT / 2);
    public static int PADDING_X = (WIDTH / 6);
    public static int PADDING_Y = (HEIGHT / 12);
    public static int DISTANCE_BETWEEN_BUTTONS = (WIDTH / 20);
    public static int BUTTON_WIDTH = (PADDING_X * 2);
    public static int BUTTON_HEIGHT = (PADDING_Y * 2);
    public static int TILE_SIZE = WIDTH / 6;
    public static int MIPLE_SIZE = WIDTH / 20;
    public static int TILE_C_MIDDLE_RATIO = WIDTH / 14;

    public static void setWidth(int width) {
        Display.WIDTH = width;
        recalculate();
    }

    public static void setHeight(int height) {
        Display.HEIGHT = height;
        recalculate();
    }

    private static void recalculate() {
        CENTER_X = (WIDTH / 2);
        CENTER_Y = (HEIGHT / 2);
        PADDING_X = (WIDTH / 6);
        PADDING_Y = (HEIGHT / 12);
        DISTANCE_BETWEEN_BUTTONS = (WIDTH / 20);
        BUTTON_WIDTH = (PADDING_X * 2);
        BUTTON_HEIGHT = (PADDING_Y * 2);
        TILE_SIZE = Math.min(WIDTH, HEIGHT) / 6;
        MIPLE_SIZE = Math.min(WIDTH, HEIGHT) / 20;
        TILE_C_MIDDLE_RATIO = Math.min(WIDTH, HEIGHT) / 14;
    }
}
