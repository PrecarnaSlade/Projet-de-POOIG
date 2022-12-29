package Common;

public enum Display {
    WIDTH(600),
    HEIGHT(600),
    CENTER_X(WIDTH.getValue() / 2),
    CENTER_Y(HEIGHT.getValue() / 2),
    PADDING_X(WIDTH.getValue() / 6),
    PADDING_Y(HEIGHT.getValue() / 12),
    DISTANCE_BETWEEN_BUTTONS(WIDTH.getValue() / 20),
    BUTTON_WIDTH(PADDING_X.getValue() * 2),
    BUTTON_HEIGHT(PADDING_Y.getValue() * 2);

    private final int value;
    Display(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
