package exceptions;

public class MyArraySizeException extends MyArrayException {

    public MyArraySizeException(String message, int i, int j) {
        super(message, i, j);
    }
}
