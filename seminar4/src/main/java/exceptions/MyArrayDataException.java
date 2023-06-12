package exceptions;

public class MyArrayDataException extends MyArrayException {
    public MyArrayDataException(String message, int i, int j) {
        super(message, i, j);
    }
}
