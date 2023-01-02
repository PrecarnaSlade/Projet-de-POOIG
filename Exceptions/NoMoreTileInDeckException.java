package Exceptions;

public class NoMoreTileInDeckException extends Exception {
    public NoMoreTileInDeckException(String errMessage) {
        super(errMessage);
        printStackTrace();
    }

}
