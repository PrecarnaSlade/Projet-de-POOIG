package Exceptions;

public class InvalidMoveException extends Exception{
    public InvalidMoveException(String errMessage) {
        super(errMessage);
    }
}
