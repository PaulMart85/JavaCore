import exceptions.MyArrayDataException;
import exceptions.MyArraySizeException;

public class ArrayOperation {

    /**
     *
     * @param array двумерный строковый массив 4х4
     * @return преобразованные в int и просуммированные значения элементов массива
     * @throws MyArraySizeException исключение при подаче массива размером, отличным от 4х4
     * @throws MyArrayDataException исключение при невозможности преобразовать элемент массива
     */
    public static long intCastingAndSummation(String[][] array) throws MyArraySizeException, MyArrayDataException {

        long sum = 0;

        if (array.length != 4 || array[0].length != 4)
            throw new MyArraySizeException("Размер массива должен быть 4х4", array.length, array[0].length);

        int i=0, j=0;
        try {
            for (i = 0; i < array.length; i++)
                for (j = 0; j < array[0].length; j++)
                    sum += Integer.parseInt(array[i][j]);
        } catch (NumberFormatException ex) {
            throw new MyArrayDataException("В массиве неверные данные для преобразования", i, j);
        }

        return sum;
    }
}
