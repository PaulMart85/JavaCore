import java.util.Random;
import java.util.Scanner;

public class Program {

    private static int WIN_COUNT; // количество фишек для победы
    private static final char DOT_HUMAN = 'X'; // у пользователя - крестики Х
    private static final char DOT_AI = 'O'; // у компьютера - нолики О
    private static final char DOT_EMPTY = '•'; // незанятое поле обозначено точкой •

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random random = new Random();

    private static char[][] field; // Двумерный массив хранит текущее состояние игрового поля
    private static int fieldSizeX; // Размерность игрового поля вдоль Х
    private static int fieldSizeY; // Размерность игрового поля вдоль Y


    public static void main(String[] args) {

        while (true) {
            initialize();
            printField();
            while (true) {
                humanTurn();
                printField();
                if (gameCheck(DOT_HUMAN, "Вы победили!"))
                    break;
                aiTurn();
                printField();
                if (gameCheck(DOT_AI, "Компьютер победил!"))
                    break;
            }

            System.out.println("Желаете сыграть еще раз? (Y - да)");
            if (!SCANNER.next().equalsIgnoreCase("Y"))
                break;
        }

    }

    /**
     * Инициализация игрового поля и условия победы
     */
    private static void initialize(){

        // Установим размерность игрового поля
        System.out.print("Введите размерность игрового поля (A х B) через пробел >>> ");
        fieldSizeX = Math.abs(SCANNER.nextInt()); // вертикаль
        fieldSizeY = Math.abs(SCANNER.nextInt()); // горизонталь

        do {
            System.out.print("Введите количество фишек для победы (начиная с 3-х и не более макс размера поля) >>> ");
            WIN_COUNT = Math.abs(SCANNER.nextInt());
        } while (WIN_COUNT > Math.max(fieldSizeX, fieldSizeY) || WIN_COUNT < 3);

        field = new char[fieldSizeX][fieldSizeY];
        // Пройдем по всем элементам массива
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                // Проинициализируем все элементы массива DOT_EMPTY (признак пустого поля)
                field[x][y] = DOT_EMPTY;
            }
        }
    }

    /**
     * Отрисовка игрового поля
     */
    private static void printField() {

        int cntDigitX = String.valueOf(fieldSizeX).length(),
                cntDigitY = String.valueOf(fieldSizeY).length();
        System.out.print("+".repeat(cntDigitX));

        for (int j = 0; j < fieldSizeY * 2 + 1; j++){
            System.out.print((j % 2 == 0) ? "-" : (" ".repeat(cntDigitY-String.valueOf(j/2+1).length()) + (j / 2 + 1)) );
        }
        System.out.println();

        for (int i = 0; i < fieldSizeX; i++) {
            System.out.print(" ".repeat(cntDigitX-String.valueOf(i+1).length()) + (i + 1) + "|");

            for (int j = 0; j <  fieldSizeY; j++)
                System.out.print(" ".repeat(cntDigitY-1) + field[i][j] + "|");

            System.out.println();
        }

        for (int j = 0; j < fieldSizeY * (2 + String.valueOf(fieldSizeY).length()-1)
                + String.valueOf(fieldSizeX).length() + 1; j++) {
            System.out.print("-");
        }
        System.out.println();

    }

    /**
     * Обработка хода пользователя (человек)
     */
    private static void humanTurn() {
        int x, y;
        do
        {
            System.out.print("Введите координаты хода X и Y через пробел >>> ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        }
        while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = DOT_HUMAN;
    }

    /**
     * Проверка, ячейка является пустой
     * @param x координата поля по вертикали
     * @param y координата поля по горизонтали
     * @return true, если ячейка пуста и false, если ячейка занята
     */
    static boolean isCellEmpty(int x, int y) {
        return field[x][y] == DOT_EMPTY;
    }

    /**
     * Проверка корректности ввода
     * (координаты хода не должны превышать размерность массива, игрового поля)
     * @param x координата поля по вертикали
     * @param y координата поля по горизонтали
     * @return true, если ход в пределах поля и false, если ход выходит за пределы поля
     */
    static boolean isCellValid(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    /**
     * Обработка хода искусственного интеллекта (компьютер)
     */
    private static void aiTurn() {

        // ai пытается помешать сопернику
        for (int i = 0; i < fieldSizeX; i++)
            for (int j = 0; j < fieldSizeY; j++)
                if (isCellEmpty(i, j)) {
                    // опережение по горизонтали
                    if (anticipation('H', i, j, DOT_HUMAN)) {field[i][j] = DOT_AI; return;}

                    // опережение по вертикали
                    if (anticipation('V', j, i, DOT_HUMAN)) {field[i][j] = DOT_AI; return;}

                    // опережение по диагонали II -> IV
                    if (anticipation('A', i, j, DOT_HUMAN)) {field[i][j] = DOT_AI; return;}

                    // опережение по диагонали I -> III
                    if (anticipation('M', i, j, DOT_HUMAN)) {field[i][j] = DOT_AI; return;}
                }

        // иначе ход компьютера c угадыванием
        // (ai ищет лучший ход для себя, если со стороны соперника нет угрозы)
        for (int i = 0; i < fieldSizeX; i++)
            for (int j = 0; j < fieldSizeY; j++)
                if (isCellEmpty(i, j)) {
                    if (anticipation('H', i, j, DOT_AI)) {field[i][j] = DOT_AI; return;}
                    if (anticipation('V', j, i, DOT_AI)) {field[i][j] = DOT_AI; return;}
                    if (anticipation('A', i, j, DOT_AI)) {field[i][j] = DOT_AI; return;}
                    if (anticipation('M', i, j, DOT_AI)) {field[i][j] = DOT_AI; return;}
                }

        // иначе случайный ход компьютера
        int x, y;
        do
        {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        }
        while (!isCellEmpty(x, y));
        field[x][y] = DOT_AI;
    }

    /**
     * Предугадать ход
     * @param type тип опережения (H - гориз, V - вертик, A - диагон1, M - диагон2)
     * @param i координата поля по вертикали
     * @param j координата поля по горизонтали
     * @param c сигнатура игрока (DOT_HUMAN или DOT_AI)
     * @return true, если опережение по заданному типу найдено, иначе false
     */
    private static boolean anticipation(char type, int i, int j, char c) {

        WIN_COUNT--; // работа на опережение
        int cntX = 0, m = 0, n = 0;
        char r;
        if (c == DOT_HUMAN) r = DOT_AI;
        else r = DOT_HUMAN;

        for (int k = j-1, l = i-1, q = 1; k > j-WIN_COUNT; k--, l--, q++) {
            switch (type) {
                case 'H': m = i; n = k; break;
                case 'V': m = k; n = i; break;
                case 'A': m = l; n = k; break;
                case 'M': m = i+q; n = k; break;
            }
            if (!isCellValid(m, n) || field[m][n] == r) break;
            if (field[m][n] == c)
                if (++cntX == WIN_COUNT-1) {WIN_COUNT++; return true;}
        }
        int tmp = cntX;
        for (int k = j+1, l = i+1, q = 1; k < j+WIN_COUNT-tmp; k++, l++, q++) {
            switch (type) {
                case 'H': m = i; n = k; break;
                case 'V': m = k; n = i; break;
                case 'A': m = l; n = k; break;
                case 'M': m = i-q; n = k; break;
            }
            if (!isCellValid(m, n) || field[m][n] == r) break;
            if (field[m][n] == c)
                if (++cntX == WIN_COUNT-1) {WIN_COUNT++; return true;}
        }
        WIN_COUNT++;
        return false;

    }

    /**
     * Проверка победы
     * @param c сигнатура игрока (DOT_HUMAN или DOT_AI)
     * @return true, если выполнено условие победы для заданного WIN_COUNT, иначе false
     */
    static boolean checkWin(char c) {

        boolean[] flagE = new boolean[]{true, false},
                flagS = new boolean[]{true, false};

        for (int i = 0; i < fieldSizeX; i++) {
            flagE[0] = true;
            for (int j = 0; j < fieldSizeY; j++)
                if (field[i][j] == c) {
                    if (flagE[0]) { // проверяем восточное направление в этой строке, если не уперлись в границу справа
                        flagE = checkEdir(c, i, j);
                        if (flagE[1]) return true; // победа игрока в восточном направлении
                    }

                    if (flagS[0]) { // проверяем южное направление в этом столбце, если не уперлись в границу снизу
                        flagS = checkSdir(c, i, j);
                        if (flagS[1]) return true; // победа игрока в южном направлении
                    }

                    if (!flagE[0] && !flagS[0]) break;

                    // если уперлись в границу справа или снизу, то нет смысла проверять в диагоналях, иначе:
                    if (flagE[0] && flagS[0]) { // проверяем по диагоналям SE и SW
                        if (checkSEdir(c, i, j)) return true; // победа игрока в юго-восточном направлении
                    }

                    // если нижняя граница свободна и есть пространство для диагонали SW
                    if (flagS[0] && j >= WIN_COUNT-1)
                        if (checkSWdir(c, i, j)) return true; // победа игрока в юго-западном направлении
                }
        }

        return false;
    }

    /**
     * Проверка победы игрока по восточному направлению Е
     * @param c сигнатура игрока (DOT_HUMAN или DOT_AI)
     * @param i текущая координата Х
     * @param j текущая координата Y
     * @return [?, true] в случае победы в восточном направлении, иначе [?, false],
     * при этом [false, false] говорит о том, что продолжать проверку в этой строке
     * в восточном направлении бессмысленно
     */
    static boolean[] checkEdir(char c, int i, int j) {
        for (int k = j+1; k < j+WIN_COUNT; k++) {
            if (!isCellValid(i,k)) return new boolean[]{false, false};
            if (field[i][k] != c) return new boolean[]{true, false};
        }

        return new boolean[]{true, true};
    }

    /**
     * Проверка победы игрока по западному направлению W
     * @param c сигнатура игрока (DOT_HUMAN или DOT_AI)
     * @param i текущая координата Х
     * @param j текущая координата Y
     * @return true в случае победы в западном направлении, иначе false
     */
    static boolean checkWdir(char c, int i, int j) {
        for (int k = j-1; k > j-WIN_COUNT; k--) {
            if (!isCellValid(i,k)) return false;
            if (field[i][k] != c) return false;
        }

        return true;
    }

    /**
     * Проверка победы игрока в южном направлении S
     * @param c сигнатура игрока (DOT_HUMAN или DOT_AI)
     * @param i текущая координата Х
     * @param j текущая координата Y
     * @return [?, true] в случае победы в южном направлении, иначе [?, false],
     * при этом [false, false] говорит о том, что продолжать проверку в этом столбце
     * в южном направлении бессмысленно
     */
    static boolean[] checkSdir(char c, int i, int j) {
        for (int k = i+1; k < i+WIN_COUNT; k++) {
            if (!isCellValid(k,j)) return new boolean[]{false, false};
            if (field[k][j] != c) return new boolean[]{true, false};
        }

        return new boolean[]{true, true};
    }

    /**
     * Проверка победы игрока в северном направлении N
     * @param c сигнатура игрока (DOT_HUMAN или DOT_AI)
     * @param i текущая координата Х
     * @param j текущая координата Y
     * @return true в случае победы в северном направлении, иначе false
     */
    static boolean checkNdir(char c, int i, int j) {
        for (int k = i-1; k > i-WIN_COUNT; k--) {
            if (!isCellValid(k,j)) return false;
            if (field[k][j] != c) return false;
        }

        return true;
    }

    /**
     * Проверка победы игрока в юго-восточном направлении SE
     * @param c сигнатура игрока (DOT_HUMAN или DOT_AI)
     * @param i текущая координата Х
     * @param j текущая координата Y
     * @return true в случае победы в юго-восточном направлении, иначе false
     */
    static boolean checkSEdir(char c, int i, int j) {
        for (int k = j+1; k < j+WIN_COUNT; k++) {
            if (!isCellValid(++i,k)) return false;
            if (field[i][k] != c) return false;
        }

        return true;
    }

    /**
     * Проверка победы игрока в северо-западном направлении NW
     * @param c сигнатура игрока (DOT_HUMAN или DOT_AI)
     * @param i текущая координата Х
     * @param j текущая координата Y
     * @return true в случае победы в северо-западном направлении, иначе false
     */
    static boolean checkNWdir(char c, int i, int j) {
        for (int k = j-1; k > j-WIN_COUNT; k--) {
            if (!isCellValid(--i,k)) return false;
            if (field[i][k] != c) return false;
        }

        return true;
    }

    /**
     * Проверка победы игрока в юго-западном направлении SW
     * @param c сигнатура игрока (DOT_HUMAN или DOT_AI)
     * @param i текущая координата Х
     * @param j текущая координата Y
     * @return true в случае победы в юго-западном направлении, иначе false
     */
    static boolean checkSWdir(char c, int i, int j) {
        for (int k = j-1; k > j-WIN_COUNT; k--) {
            if (!isCellValid(++i,k)) return false;
            if (field[i][k] != c) return false;
        }

        return true;
    }

    /**
     * Проверка победы игрока в северо-восточном направлении NE
     * @param c сигнатура игрока (DOT_HUMAN или DOT_AI)
     * @param i текущая координата Х
     * @param j текущая координата Y
     * @return true в случае победы в северо-восточном направлении, иначе false
     */
    static boolean checkNEdir(char c, int i, int j) {
        for (int k = j+1; k < j+WIN_COUNT; k++) {
            if (!isCellValid(--i,k)) return false;
            if (field[i][k] != c) return false;
        }

        return true;
    }

    /**
     * Проверка на ничью
     * @return true, если все поля заполнены, иначе false
     */
    static boolean checkDraw(){
        for (int x = 0; x < fieldSizeX; x++)
            for (int y = 0; y < fieldSizeY; y++)
                if (isCellEmpty(x, y)) return false;

        return true;
    }

    /**
     * Метод проверки состояния игры
     * @param c сигнатура игрока (DOT_HUMAN или DOT_AI)
     * @param str сообщение о победе
     * @return true, если ход привел к окончанию игры с победой или ничьей, иначе false
     */
    static boolean gameCheck(char c, String str){
        if (checkWin(c)) {
            System.out.println(str);
            return true;
        }

        if (checkDraw()) {
            System.out.println("Ничья!");
            return true;
        }

        return false; // Игра продолжается
    }

}
