import exceptions.MyArrayDataException;
import exceptions.MyArraySizeException;

public class Program {

    public static void main(String[] args) {

        String[][] stringArray = new String[][] {
                {"4", "5", "-4", "-5"},
                {"6", "8", "-7", "-6"},
                {"65", "-5", "5", "-65"},
                {"2", "3", "-2", "-3"}
        };


        try {
            System.out.printf("Сумма элементов массива составляет %d\n", ArrayOperation.intCastingAndSummation(stringArray));
        } catch (MyArraySizeException ex) {
            System.out.printf("Ошибка размерности массива: %s. Размерность введенного массива %dx%d\n",
                    ex.getMessage(), ex.getIndexI(), ex.getIndexJ());
        } catch (MyArrayDataException ex) {
            System.out.printf("Ошибка преобразования элемента массива: %s в ячейке [%d, %d]\n",
                    ex.getMessage(), ex.getIndexI(), ex.getIndexJ());
        }

    }
}
