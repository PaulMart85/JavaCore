package exceptions;

public abstract class MyArrayException extends Exception {
    private final int indexI, indexJ;

    public int getIndexI() {
        return indexI;
    }

    public int getIndexJ() {
        return indexJ;
    }

    public MyArrayException(String message, int i, int j) {
        super(message);
        indexI = i;
        indexJ = j;
    }
}
